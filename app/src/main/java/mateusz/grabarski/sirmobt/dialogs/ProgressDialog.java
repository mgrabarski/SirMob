package mateusz.grabarski.sirmobt.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import mateusz.grabarski.sirmobt.R;

/**
 * Created by MGrabarski on 30.11.2017.
 */

public class ProgressDialog extends DialogFragment {

    @BindView(R.id.dialog_progress_bar)
    ProgressBar dialogProgressBar;

    @BindView(R.id.dialog_progress_progress_tv)
    TextView progressTv;

    private ProgressDialogListener mListener;

    Unbinder unbinder;

    public static ProgressDialog newInstance() {

        Bundle args = new Bundle();

        ProgressDialog fragment = new ProgressDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof ProgressDialogListener)
            mListener = (ProgressDialogListener) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_progress, container, false);

        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void updateProgress(int progress) {
        if (dialogProgressBar == null || progressTv == null)
            return;

        dialogProgressBar.setProgress(progress);
        progressTv.setText(String.valueOf(progress));
    }

    @OnClick(R.id.dialog_progress_cancel_btn)
    public void onCancelClick() {
        dismiss();

        if (mListener != null)
            mListener.onCancelDialog();
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);

        if (mListener != null)
            mListener.onCancelDialog();
    }

    public interface ProgressDialogListener {
        void onCancelDialog();
    }
}
