package com.cst2335.yang0180;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.FrameMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ChatRoomActivity extends AppCompatActivity {

    ListView messageList;
    Button sendBtn;
    Button receiveBtn;
    EditText inputBox;
    MyDBHelper myDBHelper;
    FrameLayout frame;
    public static final String MESSAGE_TO_PASS = "MESSAGE_TO_PASS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);
        frame = findViewById(R.id.frame);
        boolean isTablet = frame != null;
        messageList = findViewById(R.id.message_list);
        sendBtn = findViewById( R.id.sendBtn);
        receiveBtn = findViewById(R.id.receiveBtn);
        inputBox = findViewById(R.id.input_content);
        myDBHelper = new MyDBHelper(getApplicationContext());
        ArrayList<Message> messages = myDBHelper.readAllMessage();
        MyAdapter adapter = new MyAdapter(messages);
        messageList.setAdapter(adapter);
        messageList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Create a bundle to pass data to the new fragment
                Bundle dataToPass = new Bundle();

                dataToPass.putSerializable(MESSAGE_TO_PASS,adapter.getItem(position));
                if(isTablet)
                {
                    DetailsFragment dFragment = new DetailsFragment(); //add a DetailFragment
                    dFragment.setArguments( dataToPass ); //pass it a bundle for information
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.frame, dFragment, "tag") //Add the fragment in FrameLayout
                            .addToBackStack(null)
                            .commit(); //actually load the fragment. Calls onCreate() in DetailFragment
                }
                else //isPhone
                {
                    Intent nextActivity = new Intent(ChatRoomActivity.this, EmptyActivity.class);
                    nextActivity.putExtra(MESSAGE_TO_PASS,adapter.getItem(position));
                    nextActivity.putExtras(dataToPass); //send data to next activity
                    startActivity(nextActivity); //make the transition
                }
            }
        });

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = inputBox.getText().toString();
                Message message = new Message(true, content);
                long id = myDBHelper.insertMessage(message);
                message.setId(id);
                inputBox.setText("");
                adapter.addMessage(message);
                adapter.notifyDataSetChanged();
            }
        });

        receiveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = inputBox.getText().toString();
                Message message = new Message(false, content);
                long id = myDBHelper.insertMessage(message);
                message.setId(id);
                inputBox.setText("");
                adapter.addMessage(message);
                adapter.notifyDataSetChanged();
            }
        });

        messageList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Message message = adapter.getItem(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(ChatRoomActivity.this);
                builder.setTitle("Do you want to delete this?")
                        .setMessage("The selected row is: "+position+"\nThe database id : "+ message.getId())
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                boolean isRemoved = myDBHelper.deleteMessage(message.getId());
                                if(isRemoved){
                                    if(isTablet){
                                        Fragment fa = getSupportFragmentManager().findFragmentByTag("tag");
                                        getSupportFragmentManager().beginTransaction().remove(fa).commit();
                                    }
                                    adapter.removeMessage(position);
                                    adapter.notifyDataSetChanged();
                                }else{
                                    Toast.makeText(ChatRoomActivity.this,"Cannot delete message",Toast.LENGTH_SHORT).show();
                                }

                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).create().show();

                return false;
            }
        });

    }

    private class MyAdapter extends BaseAdapter{

        private ArrayList<Message> messages;

        public MyAdapter(ArrayList<Message> messages) {
            this.messages = messages;
        }

        public void addMessage( Message message){
            this.messages.add(message);
        }

        public void removeMessage(int position){
            this.messages.remove(position);
        }

        @Override
        public int getCount() {
            return this.messages.size();
        }

        @Override
        public Message getItem(int position) {
            return this.messages.get(position);
        }

        @Override
        public long getItemId(int position) {
            return this.messages.get(position).getId();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

      //      if( convertView == null){
                LayoutInflater inflater = getLayoutInflater();
                Message message = getItem(position);
                if( message.isSend()){
                    convertView = inflater.inflate(R.layout.row_send_layout,parent,false);
                    TextView messageContent = convertView.findViewById(R.id.send_msg_content);
                    messageContent.setText(message.getContent());
                }else{
                    convertView = inflater.inflate(R.layout.row_receive_layout,parent,false);
                    TextView messageContent = convertView.findViewById(R.id.receive_msg_content);
                    messageContent.setText(message.getContent());
                }
        //    }

            return convertView;
        }
    }
}