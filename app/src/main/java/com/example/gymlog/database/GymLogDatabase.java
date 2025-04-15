package com.example.gymlog.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.gymlog.database.entities.GymLog;
import com.example.gymlog.database.entities.User;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {GymLog.class, User.class}, version = 1)
@TypeConverters(DateConverter.class)
public abstract class GymLogDatabase extends RoomDatabase {

    public abstract GymLogDAO gymLogDAO();
    public abstract UserDAO userDAO();

    private static volatile GymLogDatabase INSTANCE;
    private static final ExecutorService databaseWriteExecutor = Executors.newSingleThreadExecutor();

    private static final RoomDatabase.Callback addDefaultUsers = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            databaseWriteExecutor.execute(() -> {
                UserDAO dao = INSTANCE.userDAO();

                User admin = new User("admin1", "admin1", true);
                dao.insert(admin);

                User testUser = new User("user1", "user1", false);
                dao.insert(testUser);

            });
        }
    };

    public static GymLogDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (GymLogDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    GymLogDatabase.class, "gym_log_database")
                            .addCallback(addDefaultUsers)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
