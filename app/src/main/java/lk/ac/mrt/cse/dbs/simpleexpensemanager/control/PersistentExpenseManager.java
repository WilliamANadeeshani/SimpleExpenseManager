package lk.ac.mrt.cse.dbs.simpleexpensemanager.control;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import lk.ac.mrt.cse.dbs.simpleexpensemanager.control.exception.ExpenseManagerException;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl.PersistentAccountDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl.PersistentTransactionDAO;

/**
 * Created by William A Nadeeshani on 2016-11-20.
 */
public class PersistentExpenseManager extends ExpenseManager{
        private static final String DATABASE_NAME= "140406M.db";
        private static final String Table_NAME= "Account";
        private static final String COL_1= "Account_no";
        private static final String COL_2= "Bank";
        private static final String COL_3= "Owner";
        private static final String COL_4= "Initial_amt";

        private Context context;

        public PersistentExpenseManager(Context context){
            this.context = context;
            this.setup();
        }

        @Override
        public void setup(){

            //open the existingDataBase
            SQLiteDatabase myDb = context.openOrCreateDatabase(DATABASE_NAME, context.MODE_PRIVATE, null);

            //If there isn't such a Database Create the Database
            myDb.execSQL("CREATE TABLE IF NOT EXISTS" + Table_NAME +
                    COL_1 + " VARCHAR PRIMARY KEY," +
                    COL_2 + " VARCHAR," +
                    COL_3 + " VARCHAR," +
                    COL_4 + " REAL" +
                    ");"
            );

            myDb.execSQL("CREATE TABLE IF NOT EXISTS TransactionLog(" +
                    "Transaction_id INTEGER PRIMARY KEY," +
                    "Account_no VARCHAR," +
                    "Type INT," +
                    "Amt REAL," +
                    "Log_date DATE," +
                    "FOREIGN KEY (Account_no) REFERENCES Account(Account_no)" +
                    ");"
            );

            setAccountsDAO(new PersistentAccountDAO(myDb));
            setTransactionsDAO(new PersistentTransactionDAO(myDb));
        }
}
