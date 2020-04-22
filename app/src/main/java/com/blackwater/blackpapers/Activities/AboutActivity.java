package com.blackwater.blackpapers.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.blackwater.blackpapers.R;


public class AboutActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton btnWeb,btnEmail,btnInstagram,btnTwitter,btnFacebook,btnEmailYgz,btnInstagramYgz,btnTwitterYgz,btnFacebookYgz,btnEmailEge,btnInstagramEge,btnTwitterEge,btnFacebookEge;
    Button btnOSL,btnLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() !=null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gmailIntent = new Intent(Intent.ACTION_SEND)
                        .putExtra(Intent.EXTRA_EMAIL, new String[]{"incblackwater@gmail.com"})
                        .putExtra(Intent.EXTRA_SUBJECT, "[BlackPapers]BETA PROGRAM")
                        .putExtra(Intent.EXTRA_TEXT,"Hi,");
                gmailIntent.setType("message/rfc822");
                startActivity(Intent.createChooser(gmailIntent, "Send with"));
            }
        });

        btnLog = (Button)findViewById(R.id.btn_log);
        btnWeb = (ImageButton)findViewById(R.id.btn_website);
        btnEmail = (ImageButton)findViewById(R.id.btn_email);
        btnInstagram = (ImageButton)findViewById(R.id.btn_instagram);
        btnTwitter = (ImageButton)findViewById(R.id.btn_twitter);
        btnFacebook = (ImageButton)findViewById(R.id.btn_facebook);
        btnEmailYgz = (ImageButton)findViewById(R.id.btn_email_ygz);
        btnInstagramYgz = (ImageButton)findViewById(R.id.ig_ygz);
        btnTwitterYgz = (ImageButton)findViewById(R.id.btn_twitter_ygz);
        btnFacebookYgz = (ImageButton)findViewById(R.id.btn_facebook_ygz);
        btnEmailEge = (ImageButton)findViewById(R.id.btn_email_ege);
        btnInstagramEge = (ImageButton)findViewById(R.id.btn_ig_ege);
        btnTwitterEge = (ImageButton)findViewById(R.id.btn_twitter_ege);
        btnFacebookEge = (ImageButton)findViewById(R.id.btn_facebook_ege);
        btnOSL = (Button) findViewById(R.id.btn_osl);

        btnWeb.setOnClickListener(this);
        btnEmail.setOnClickListener(this);
        btnInstagram.setOnClickListener(this);
        btnTwitter.setOnClickListener(this);
        btnFacebook.setOnClickListener(this);
        btnEmailYgz.setOnClickListener(this);
        btnInstagramYgz.setOnClickListener(this);
        btnTwitterYgz.setOnClickListener(this);
        btnFacebookYgz.setOnClickListener(this);
        btnEmailEge.setOnClickListener(this);
        btnInstagramEge.setOnClickListener(this);
        btnTwitterEge.setOnClickListener(this);
        btnFacebookEge.setOnClickListener(this);
        btnOSL.setOnClickListener(this);
        btnLog.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }


    //TODO: assign button click events!!!
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_website:
                Toast.makeText(this, "Launch website", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_email:
                Intent gmail3Intent = new Intent(Intent.ACTION_SEND)
                        .putExtra(Intent.EXTRA_EMAIL, new String[]{"incblackwater@gmail.com"})
                        .putExtra(Intent.EXTRA_SUBJECT, "[BlackPapers]BETA PROGRAM")
                        .putExtra(Intent.EXTRA_TEXT,"Hi,");
                gmail3Intent.setType("message/rfc822");
                startActivity(Intent.createChooser(gmail3Intent, "Send with"));
                break;
            case R.id.btn_instagram:
                Intent btn_instagram = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/blackwaterinc/"));
                startActivity(btn_instagram);
                break;
            case R.id.btn_twitter:
                Intent btn_twitter = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/IncBlackwater/"));
                startActivity(btn_twitter);
                break;
            case R.id.btn_facebook:
                Toast.makeText(this, "Launch facebook", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_email_ygz:
                Intent gmail5Intent = new Intent(Intent.ACTION_SEND)
                        .putExtra(Intent.EXTRA_EMAIL, new String[]{"y-simsek-54@hotmail.com"})
                        .putExtra(Intent.EXTRA_SUBJECT, "[BlackPapers]BETA PROGRAM")
                        .putExtra(Intent.EXTRA_TEXT,"Hi,");
                gmail5Intent.setType("message/rfc822");
                startActivity(Intent.createChooser(gmail5Intent, "Send with"));
                break;
            case R.id.ig_ygz:
                Toast.makeText(this, "Launch IG Profile", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_twitter_ygz:
                Toast.makeText(this, "Launch Twitter", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_facebook_ygz:
                Toast.makeText(this, "Launch facebook", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_email_ege:
                Intent gmail2Intent = new Intent(Intent.ACTION_SEND)
                        .putExtra(Intent.EXTRA_EMAIL, new String[]{"ege.bicakci54@gmail.com"})
                        .putExtra(Intent.EXTRA_SUBJECT, "[BlackPapers]BETA PROGRAM")
                        .putExtra(Intent.EXTRA_TEXT,"Hi,");
                gmail2Intent.setType("message/rfc822");
                startActivity(Intent.createChooser(gmail2Intent, "Send with"));
                break;
            case R.id.btn_ig_ege:
                Intent btn_ig_ege = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/potato.camellia/"));
                startActivity(btn_ig_ege);
                break;
            case R.id.btn_twitter_ege:
                Intent btn_twitter_ege = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/Todo_Proudfoot/"));
                startActivity(btn_twitter_ege);
                break;
            case R.id.btn_facebook_ege:
                Intent btn_facebook_ege = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/egebicakci/"));
                startActivity(btn_facebook_ege);
                break;
            case R.id.btn_osl:
                Toast.makeText(this, "Show OSL", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_log:
                TextView msg = new TextView(this);
                msg.setText(Html.fromHtml("<p>*\\\\--21/08/2019 - 0.5 release--/*</p>\n" +
                        "<p>!!!IMPORTANT!!!</p>\n" +
                        "<p>Database will be changed after next update so old versions won't work</p>\n" +
                        "<p>--New Wallpapers Tab--</p>\n" +
                        "<p>*\\\\--21/08/2019 - 0.4 release--/*</p>\n" +
                        "<p>--Bug fixes--</p>\n" +
                        "<p>--Added SettingsActivity--</p>\n" +
                        "<p>--Design improvements--</p>\n" +
                        "<p>*\\\\--25/07/2019 - 0.3.4 release--/*</p>\n" +
                        "<p>--App tab reworked--</p>\n" +
                        "<p>--Added Upload Activity--</p>\n" +
                        "<p>--Popular tab--</p>\n" +
                        "<p>--Minor bug fixes--</p>\n" +
                        "<p>*\\\\--24/07/2019 - 0.3 release--/*</p>\n" +
                        "<p>--Added app tab--</p>\n" +
                        "<p>--Added AboutActivity--</p>\n" +
                        "<p>--LOTS OF BUG FIXES--</p>\n" +
                        "<p>*\\\\--22/07/2019 - 0.2.4 release--/*</p>\n" +
                        "<p>--New fresh look--</p>\n" +
                        "<p>--Rebuilded app--</p>\n" +
                        "<p>--Redesigned everything--</p>"));
                AlertDialog.Builder ab = new AlertDialog.Builder(this);
                ab.setView(msg);
                ab.setCancelable(true);
                ab.setNeutralButton("OK", null);
                ab.show();
                break;
        }
    }
}
