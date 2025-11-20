package com.aksoftwaresolution.wellpick.activity;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.aksoftwaresolution.wellpick.R;

public class PrivacyPolicyActivity extends AppCompatActivity {
    ImageView iconBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView tvPrivacy = findViewById(R.id.tvPrivacy);
        iconBack=findViewById(R.id.iconBack);

        iconBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });




        String htmlPolicy =
                "<h2><b>Privacy Policy</b></h2>" +
                        "<p><i>Last Updated: November 16, 2025</i></p>" +

                        "<p>Ak SoftwareSolution built the <b>Wellpick </b> app as a Commercial app. "
                        + "This SERVICE is provided by Ak SoftwareSolution and is intended for use as is.</p>"

                        + "<p>This page informs visitors regarding our policies with the collection, use, "
                        + "and disclosure of Personal Information for anyone deciding to use our Service.</p>"

                        + "<p>If you choose to use our Service, then you agree to the collection and use of "
                        + "information in relation to this policy. The Personal Information that we collect is "
                        + "used for providing and improving the Service. We will not use or share your information "
                        + "with anyone except as described in this Privacy Policy.</p>"

                        + "<p>The terms used in this Privacy Policy have the same meanings as in our Terms and Conditions, "
                        + "unless otherwise defined.</p>"

                        + "<h3><b>Information Collection and Use</b></h3>"
                        + "<p>For a better experience, we may require you to provide certain personally identifiable information, "
                        + "including but not limited to <b>Device IDs  Name, Email Address </b>. The information we request will be retained by "
                        + "Ak SoftwareSolution (www.AkSoftwareSolution.com) and used as described in this policy.</p>"

                        + "<p>The app uses third-party services that may collect information used to identify you.</p>"

                        + "<b>Third-party services used:</b>"
                        + "<ul>"
                        + "<li>Google Play Services</li>"
                        + "<li>AdMob</li>"
                        + "<li>Google Analytics for Firebase</li>"
                        + "</ul>"

                        + "<h3><b>Log Data</b></h3>"
                        + "<p>Whenever you use our Service, in case of an error we collect data called Log Data. "
                        + "This may include your device’s IP address, device name, OS version, app configuration, "
                        + "time and date of use, and other statistics.</p>"

                        + "<h3><b>Cookies</b></h3>"
                        + "<p>We do not explicitly use cookies. However, third-party libraries may use cookies to improve their services. "
                        + "You can accept or refuse cookies. Refusing cookies may limit some features.</p>"

                        + "<h3><b>Service Providers</b></h3>"
                        + "<p>We may employ third-party companies and individuals for:</p>"
                        + "<ul>"
                        + "<li>Facilitating our Service</li>"
                        + "<li>Providing the Service on our behalf</li>"
                        + "<li>Performing Service-related tasks</li>"
                        + "<li>Analyzing how our Service is used</li>"
                        + "</ul>"

                        + "<p>These third parties may have access to your Personal Information but are obligated not to disclose or use it for any other purpose.</p>"

                        + "<h3><b>Security</b></h3>"
                        + "<p>We value your trust in providing your Personal Information and use commercially acceptable means to protect it. "
                        + "But no method of transmission or storage is 100% secure.</p>"

                        + "<h3><b>Links to Other Sites</b></h3>"
                        + "<p>This Service may contain links to external sites. We strongly advise reviewing the Privacy Policy of those websites. "
                        + "We have no control over their content or practices.</p>"

                        + "<h3><b>Children’s Privacy</b></h3>"
                        + "<p>We do not knowingly collect information from children under 13. If we discover such data, we delete it immediately.</p>"

                        + "<h3><b>Changes to This Privacy Policy</b></h3>"
                        + "<p>We may update this Privacy Policy from time to time. Effective: <b>2025-11-16</b>.</p>"

                        + "<h3><b>Data Deletion Right</b></h3>"
                        + "<p>You have the right to access, rectify, object to, or erase your personal data. "
                        + "To request deletion, email us at: <b> abulkhair77912@gmail.com</b></p>"

                        + "<h3><b>Contact Us</b></h3>"+
                        "<p>If you have any questions or suggestions, please contact us at: "
                        + "abulkhair77912@gmail.com</b></p>";

        tvPrivacy.setText(Html.fromHtml(htmlPolicy));


    }
}
