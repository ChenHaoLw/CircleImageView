package demos.ch.com.drawabledemo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.drawable.ScaleDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.RemoteViews;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

  Button button;
  TextView tv;
  RelativeLayout layout;
  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    initView();
    //startNotification();
  }

  private void initView() {
    button = (Button)findViewById(R.id.btn);
    button.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        TransitionDrawable drawable = (TransitionDrawable) tv.getBackground();
        drawable.startTransition(1500);

        ScaleDrawable scaleDrawable = (ScaleDrawable) layout.getBackground();
        scaleDrawable.setLevel(1);
      }
    });

    tv = (TextView)findViewById(R.id.tv);

    layout = (RelativeLayout) findViewById(R.id.activity_main);
  }

  private void startNotification() {
    NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    Notification notification = new Notification();
    notification.icon = R.mipmap.ic_launcher;

    PendingIntent pendingIntent = PendingIntent.getActivity(this,0,
        new Intent(this,MainActivity.class),PendingIntent.FLAG_UPDATE_CURRENT);

    RemoteViews remoteView = new RemoteViews(getPackageName(),R.layout.remote_view);
    remoteView.setTextViewText(R.id.tv,"Test RemoteViews in notification");
    remoteView.setImageViewResource(R.id.iv,R.mipmap.f);
    remoteView.setOnClickPendingIntent(R.id.tv,pendingIntent);
    notification.contentView = remoteView;

    notification.contentIntent = pendingIntent;
    manager.notify(1,notification);
  }
}
