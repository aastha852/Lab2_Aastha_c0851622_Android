package com.aasthachatters.labtest2final;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
    EditText name, ID, Description, Price;
    Button insert, update, delete, view;
    DBhelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.name);
        ID = findViewById(R.id.ID);
        Description= findViewById(R.id.Description);
        Price= findViewById(R.id.Price);
        insert = findViewById(R.id.btnInsert);
        update = findViewById(R.id.btnUpdate);
        delete = findViewById(R.id.btnDelete);
        view = findViewById(R.id.btnView);
        DB = new DBhelper(this);
        Cursor res2 = DB.getdata();
        StringBuffer buffer2 = new StringBuffer();
        while(res2.moveToNext()){
            buffer2.append("Name :"+res2.getString(0)+"\n");
            buffer2.append("Description :"+res2.getString(1)+"\n");
            buffer2.append("ID :"+res2.getString(2)+"\n\n");
            buffer2.append("Price :"+res2.getString(3)+"\n\n");
        }

        AlertDialog.Builder builder2 = new AlertDialog.Builder(MainActivity.this);
        builder2.setCancelable(true);
        builder2.setTitle("User Entries");
        builder2.setMessage(buffer2.toString());
        builder2.show();
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String IDTXT = ID.getText().toString();
                String DescriptionTXT = Description.getText().toString();
                String PriceTXT = Price.getText().toString();

                Boolean checkinsertdata = DB.insertuserdata(nameTXT, IDTXT, DescriptionTXT, PriceTXT);
                if(checkinsertdata==true)
                    Toast.makeText(MainActivity.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
            }        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String DescriptionTXT = Description.getText().toString();
                String IDTXT = ID.getText().toString();
                String PriceTXT = Price.getText().toString();

                Boolean checkupdatedata = DB.updateuserdata(nameTXT,IDTXT, DescriptionTXT, PriceTXT);
                if(checkupdatedata==true)
                    Toast.makeText(MainActivity.this, "Entry Updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "New Entry Not Updated", Toast.LENGTH_SHORT).show();
            }        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                Boolean checkdeletedata = DB.deletedata(nameTXT);
                if(checkdeletedata==true)
                    Toast.makeText(MainActivity.this, "Entry Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Entry Not Deleted", Toast.LENGTH_SHORT).show();
            }        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();
                if(res.getCount()==0){
                    Toast.makeText(MainActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Name :"+res.getString(0)+"\n");
                    buffer.append("Description :"+res.getString(1)+"\n");
                    buffer.append("ID :"+res.getString(2)+"\n\n");
                    buffer.append("Price :"+res.getString(3)+"\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }        });
    }}




