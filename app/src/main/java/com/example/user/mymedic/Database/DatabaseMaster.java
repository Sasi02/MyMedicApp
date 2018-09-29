package com.example.user.mymedic.Database;

import android.provider.BaseColumns;

public final class DatabaseMaster {

    private DatabaseMaster(){}

    public static class Users implements BaseColumns{

        public static final String TABLE_NAME = "users";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_FNAME = "fname";
        public static final String COLUMN_NAME_LNAME = "lname";
        public static final String COLUMN_NAME_INITIALS = "initials";
        public static final String COLUMN_NAME_DOB = "dob";
        public static final String COLUMN_NAME_PHONE = "phone";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_GENDER = "gender";
        public static final String COLUMN_NAME_BGROUP = "bloodGroup";
        public static final String COLUMN_NAME_GENDISEASES = "genDiseases";
        public static final String COLUMN_NAME_ALLERGIES = "allergies";
        public static final String COLUMN_NAME_OPERATIONS = "operations";
    }

    public static class DrugScheduleHours implements BaseColumns{

        public static final String TABLE_NAME = "drugSheduleHours";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_METHOD = "method";
        public static final String COLUMN_NAME_NOOFPILLS = "noOfPills";
        public static final String COLUMN_NAME_DURATION = "duration";
        public static final String COLUMN_NAME_PRESCRIPTION_ID = "prescriptionId";
        public static final String COLUMN_NAME_DRUG_ID = "drugId";
    }

    public static class DrugScheduleMeal implements BaseColumns{

        public static final String TABLE_NAME = "drugSheduleMeals";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_METHOD = "method";
        public static final String COLUMN_NAME_NOOFPILLS = "noOfPills";
        public static final String COLUMN_NAME_SELECTEDMEAL = "selectedMeal";
        public static final String COLUMN_NAME_BEFOREORAFTER = "beforeOrAfter";
        public static final String COLUMN_NAME_PRESCRIPTION_ID = "prescriptionId";
        public static final String COLUMN_NAME_DRUG_ID = "drugId";
    }

    public static class Drug implements BaseColumns{

        public static final String TABLE_NAME = "drugs";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_MANUFACTURER = "manufacturer";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_DOSAGE = "dosage";
    }

    public static class Prescription implements BaseColumns{

        public static final String TABLE_NAME = "priscriptions";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_DOCNAME = "docName";
        public static final String COLUMN_NAME_DISEASE = "disease";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_SDATE = "startDate";
        public static final String COLUMN_NAME_EDATE = "endDate";
        public static final String COLUMN_NAME_USER_ID = "userId";
    }


}
