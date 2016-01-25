package com.student.surya.studenthelp;

import android.app.backup.BackupAgentHelper;
import android.app.backup.FileBackupHelper;

/**
 * Created by Surya on 11/21/2015.
 */
public class BackupAgent extends BackupAgentHelper {
    static final String classes = "classesTest.txt";
    static final String exams = "examsTest.txt";
    static final String tasks = "tasksTest.txt";
    static final String backupKey = "studentHelpFiles";

    public void onCreate(){
        FileBackupHelper helper = new FileBackupHelper(this, classes, exams, tasks, backupKey);
        addHelper(backupKey, helper);
    }
}
