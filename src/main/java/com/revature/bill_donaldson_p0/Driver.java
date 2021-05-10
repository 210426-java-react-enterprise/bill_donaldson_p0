package com.revature.bill_donaldson_p0;
import java.io.*;

/* Overview of current status of Project:

    1. Either login or register.
    2. Once logged in go to transaction options.
    3. The user will be able to deposit, withdrawal, check balance, and list all transactions.

    The absolute key to this implementation is the current user table.  The table can contain no
    more than one user.  This user will persist ONLY during the time that the customer is logged
    into the system.  As soon as the customer is finished, then that person is removed from the
    table.
 */



import com.revature.bill_donaldson_p0.util.AppState;


    public class Driver {

        private static AppState app = new AppState();

        public static void main(String[] args) {
            while (app.isAppRunning()) {
                app.getRouter().navigate("/welcome");
            }
        }

        public static AppState app() {
            return app;
        }
    }

