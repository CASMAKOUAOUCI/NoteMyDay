package ka.ti.asma.univ8.notemyday.Fragments;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import ka.ti.asma.univ8.notemyday.R;

public class AproposFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_apropos, container, false);
        getActivity().setTitle("À PROPOS");

        FloatingActionButton sendMail = (FloatingActionButton) view.findViewById(R.id.fragment_Apropos_fab);
        sendMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"noteMyDay@gmail.com"});
                i.putExtra(Intent.EXTRA_SUBJECT, "Noter ma journée");
                i.putExtra(Intent.EXTRA_TEXT   , "");
                try {
                    startActivity(Intent.createChooser(i, "Envoi email..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getContext(), "Aucun email client instalé.", Toast.LENGTH_SHORT).show();
                }

            }
        });

        EditText editText = (EditText) view.findViewById(R.id.fragment_Apropos_EditText);

        String applicationName = "NOTER MA JOURNEÉ";
        PackageInfo pInfo = null;
        try {
            pInfo = getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String version = pInfo.versionName;

        String applicationDescription = "est une application crée en 2016 par";
        String dev1 = "KOUAOUCI Asma Amina";
        String dev2 = "KAIS Tiziri";
        String dev3 = "MOHAMMEDI Kahina";

        String enseignant = "Enseignant : Guillaume Besacier";

        editText.append(Html.fromHtml("<b> <br /> " + applicationName +"<br /> "  +" version " + version + "</b> <br /><br />"
                +applicationDescription  + "<b> <br /><br /> " +
                dev1 + "<b><br /> <br />"
                + dev2 + "<b> <br /><br /> "
                + dev3 +  "<b> <br /><br /> "
                +enseignant));

        return view;
    }

}
