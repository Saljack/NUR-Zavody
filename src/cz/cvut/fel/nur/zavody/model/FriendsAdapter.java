/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.nur.zavody.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import cz.cvut.fel.nur.zavody.R;
import java.util.List;

/**
 * Adapter pratel pro zobrazeni v ListView
 * @author Saljack
 */
public class FriendsAdapter extends ArrayAdapter<Friend> {

    private LayoutInflater _inflater;
    private Context _ctx;

    public FriendsAdapter(Context context, List<Friend> objects, LayoutInflater inflater) {
        super(context, R.layout.friend_line, objects);
        _inflater = inflater;
        _ctx = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = _inflater.inflate(R.layout.friend_line, parent, false);
            //TODO add SizeBar
            convertView.setTag(new FriendWrapper(convertView));
        }
        FriendWrapper ui = (FriendWrapper) convertView.getTag();
        Friend item = getItem(position);
        ui.getName().setText(item.getName());
        ImageView social = ui.getSocial();
        switch (item.getSocial()) {
            case FACEBOOK:
                social.setImageDrawable(_ctx.getResources().getDrawable(R.drawable.facebook));
                break;
            case TWITTER:
                social.setImageDrawable(_ctx.getResources().getDrawable(R.drawable.twitter));
                break;
        }

        return convertView;
    }

    /**
     * Wrapper, ktery si drzi prednactena View aby se nemusel pokazde inflatovat
     */
    private class FriendWrapper {

        private View _view;
        private ImageView _photo;
        private ImageView _social;
        private TextView _name;

        public FriendWrapper(View _view) {
            this._view = _view;
        }

        public View getView() {
            return _view;
        }

        public ImageView getPhoto() {
            if (_photo == null) {
                _photo = (ImageView) _view.findViewById(R.id.friend_photo);
            }
            return _photo;
        }

        public ImageView getSocial() {
            if (_social == null) {
                _social = (ImageView) _view.findViewById(R.id.friend_social);
            }
            return _social;
        }

        public TextView getName() {
            if (_name == null) {
                _name = (TextView) _view.findViewById(R.id.friend_name);
            }
            return _name;
        }
    }
}
