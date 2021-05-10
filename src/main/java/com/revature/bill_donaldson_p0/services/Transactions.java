package com.revature.bill_donaldson_p0.services;

import com.revature.bill_donaldson_p0.screens.LoginScreen;

import java.io.BufferedReader;

public class Transactions{

    /* SQL code for making a deposit:
    create table temp as
  select ssn, transdate, a.amount + 100 as amount, account_active from public.savings as a
  where ssn = '123456789';

 select * from temp;

delete from savings where ssn = '123456789';

insert into savings select * from temp;

     */

    /* Here is the code for making a withdrawl
    drop table if exists temp;
create table temp as
  select ssn, transdate, a.amount - 100 as amount, account_active from public.savings as a
  where ssn = '123456789';

 select * from temp;

delete from savings where ssn = '123456789';

insert into savings select * from temp;

select * from savings;


     */
   public Transactions(){
       System.out.println("You made it into the constructor class for transactions.");
   }
   public void Transaction_choices(){
       System.out.println("You are within the body of the transaction class");
       System.out.println();
       System.out.println();
       System.out.println();
       System.out.println();


   }
}
