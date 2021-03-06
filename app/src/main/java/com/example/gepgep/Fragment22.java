package com.example.gepgep;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.Settings;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.gepgep.databinding.Fragment22Binding;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;
import java.util.regex.Pattern;

import app.akexorcist.bluetotohspp.library.BluetoothSPP;


public class Fragment22 extends Fragment {
    private Fragment22Binding binding;
    private BluetoothSPP bluetoothSPP;


    private int on_off_chk = 0;
    private int img[] = {R.drawable.d0, R.drawable.d1, R.drawable.d2, R.drawable.d3, R.drawable.d4, R.drawable.d5, R.drawable.d6, R.drawable.d7,
            R.drawable.d8, R.drawable.d9, R.drawable.d10, R.drawable.d11, R.drawable.d12, R.drawable.d13, R.drawable.d14, R.drawable.d15,
            R.drawable.d16, R.drawable.d17, R.drawable.d18, R.drawable.d19, R.drawable.d20, R.drawable.d21, R.drawable.d22, R.drawable.d23,
            R.drawable.d24, R.drawable.d25, R.drawable.d26, R.drawable.d27, R.drawable.d28, R.drawable.d29, R.drawable.d30, R.drawable.d31,
            R.drawable.d32, R.drawable.d33, R.drawable.d34, R.drawable.d35, R.drawable.d36, R.drawable.d37, R.drawable.d38, R.drawable.d39,
            R.drawable.d40, R.drawable.d41, R.drawable.d42, R.drawable.d43, R.drawable.d44, R.drawable.d45, R.drawable.d46, R.drawable.d47,
            R.drawable.d48, R.drawable.d49, R.drawable.d50, R.drawable.d51, R.drawable.d52, R.drawable.d53, R.drawable.d54, R.drawable.d55,
            R.drawable.d56, R.drawable.d57, R.drawable.d58, R.drawable.d59, R.drawable.d60, R.drawable.d61, R.drawable.d62, R.drawable.d63
    };

    // ?????? ??????
    static public String[] Send_22_to_21 = {"", "", "", "", ""};
    // ?????? ??????
    public InputFilter filterAlphaNum = new InputFilter() {
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            Pattern ps = Pattern.compile("^[???-??????-???0-9]*$");
            if (!ps.matcher(source).matches()) {
                return "";
            }
            return null;
        }
    };

    private void hideKeyboard(EditText edit) {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(edit.getWindowToken(), 0);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_22, container, false);
        bluetoothSPP = new BluetoothSPP(getActivity());
        View root = binding.getRoot();

        bluetoothSPP.setOnDataReceivedListener((data, message) -> {
            //????????? ????????????
            Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
        });

        binding.editLabelText.setFilters(new InputFilter[]{filterAlphaNum}); // ?????? ??????
        binding.editLabelText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 6) {
                    binding.editLabelText.setText(binding.editLabelText.getText().toString().substring(0, 6));
                    binding.editLabelText.setSelection(binding.editLabelText.length());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                binding.checkTextEditText.setText(binding.editLabelText.getText());
                String editZero = binding.editLabelText.getText().toString();
                if (editZero.length() == 0) {
                    binding.print.setBackgroundResource(R.drawable.back3_round30_line);
                } else {
                    binding.print.setBackgroundResource(R.drawable.skin_round);
                }
            }
        });

        //??????????????? ????????? ?????????????????? ????????? ?????????
        binding.editLabelText.post(new Runnable() {
            @Override
            public void run() {
                binding.editLabelText.setFocusableInTouchMode(true);
                binding.editLabelText.requestFocus();

                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

                imm.showSoftInput(binding.editLabelText, 0);

            }
        });

        //??????????????? ????????? ?????????
        binding.editLabelText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((keyCode == KeyEvent.KEYCODE_ENTER)) {
                    hideKeyboard(binding.editLabelText);
                    return true;
                }
                return false;
            }
        });

        if (Fragment21.before == 1) {
            binding.editLabelText.setText(Fragment21.Send_21_to_22); // ???????????? ?????? ?????? 21 ??????????????? ?????? ????????????.
            Fragment21.before = 0;
        }


        binding.print.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // ?????? ????????? ?????? ??????
                boolean index = true;
                if (Send_22_to_21[0].equals(binding.editLabelText.getText().toString()) || Send_22_to_21[1].equals(binding.editLabelText.getText().toString()) || Send_22_to_21[2].equals(binding.editLabelText.getText().toString()) ||
                        Send_22_to_21[3].equals(binding.editLabelText.getText().toString()) || Send_22_to_21[4].equals(binding.editLabelText.getText().toString())) {
                    index = false;
                }
                if (index) {
                    for (int i = 0; i < 4; i++) // ????????????
                    {
                        Send_22_to_21[4 - i] = Send_22_to_21[3 - i];
                    }
                    Send_22_to_21[0] = binding.editLabelText.getText().toString();
                }

                // ????????? ?????? ??????
                text2dots.disassemble_korean(binding.editLabelText.getText().toString().trim()); // ???????????? ?????? ???????????? ?????? ??????

                if (on_off_chk == 1) {
                    text2dots.korea_disassemble2dots_data(text2dots.KOREA_disassmble);
                } else if (on_off_chk == 0) {
                    text2dots.korea_disassemble2dots_data_basic(text2dots.KOREA_disassmble);
                }

                binding.dot0.setImageResource(R.drawable.d0);
                binding.dot1.setImageResource(R.drawable.d0);
                binding.dot2.setImageResource(R.drawable.d0);
                binding.dot3.setImageResource(R.drawable.d0);
                binding.dot4.setImageResource(R.drawable.d0);
                binding.dot5.setImageResource(R.drawable.d0);
                binding.dot6.setImageResource(R.drawable.d0);
                binding.dot7.setImageResource(R.drawable.d0);
                binding.dot8.setImageResource(R.drawable.d0);
                binding.dot9.setImageResource(R.drawable.d0);
                binding.dot10.setImageResource(R.drawable.d0);
                binding.dot11.setImageResource(R.drawable.d0);
                binding.dot12.setImageResource(R.drawable.d0);
                binding.dot13.setImageResource(R.drawable.d0);
                binding.dot14.setImageResource(R.drawable.d0);
                binding.dot15.setImageResource(R.drawable.d0);

                for (int i = 0; i < 16; i++) {
                    if (text2dots.dots_datas[i] == 0) break;


                    int data = text2dots.dots_datas[i];
                    int test = 1;
                    switch (i) {
                        case 0:
                            binding.dot0.setImageResource(img[data]);
                            break;
                        case 1:
                            binding.dot1.setImageResource(img[data]);
                            break;
                        case 2:
                            binding.dot2.setImageResource(img[data]);
                            break;
                        case 3:
                            binding.dot3.setImageResource(img[data]);
                            break;
                        case 4:
                            binding.dot4.setImageResource(img[data]);
                            break;
                        case 5:
                            binding.dot5.setImageResource(img[data]);
                            break;
                        case 6:
                            binding.dot6.setImageResource(img[data]);
                            break;
                        case 7:
                            binding.dot7.setImageResource(img[data]);
                            break;
                        case 8:
                            binding.dot8.setImageResource(img[data]);
                            break;
                        case 9:
                            binding.dot9.setImageResource(img[data]);
                            break;
                        case 10:
                            binding.dot10.setImageResource(img[data]);
                            break;
                        case 11:
                            binding.dot11.setImageResource(img[data]);
                            break;
                        case 12:
                            binding.dot12.setImageResource(img[data]);
                            break;
                        case 13:
                            binding.dot13.setImageResource(img[data]);
                            break;
                        case 14:
                            binding.dot14.setImageResource(img[data]);
                            break;
                        case 15:
                            binding.dot15.setImageResource(img[data]);
                            break;
                    }
                }

//                ConnectedThread connectedThread = new ConnectedThread(BluetoothManager.getInstance());




            }
        });

        binding.abbreTextView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (on_off_chk == 0) {
                    binding.abbreTextView.setText("?????? ??????");
                    binding.abbreTextView.setTextColor(getResources().getColor(R.color.skin_1));
                    on_off_chk = 1;
                    binding.abbreTextView.setBackgroundResource(R.drawable.skin_round_line);
                } else if (on_off_chk == 1) {
                    binding.abbreTextView.setText("?????? ??????");
                    binding.abbreTextView.setTextColor(getResources().getColor(R.color.text));
                    on_off_chk = 0;
                    binding.abbreTextView.setBackgroundResource(R.drawable.back3_round30_line);
                }

            }
        });

        binding.preView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.frameLayout2, new Fragment21()).commit();
            }
        });


        return root;
    }

    private class ConnectedThread extends Thread {
        private final BluetoothSocket mmSocket;
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;
        private byte[] mmBuffer;

        public ConnectedThread(BluetoothSocket socket) {
            mmSocket = socket;
            InputStream tmpIn = null;
            OutputStream tmpOut = null;

            // Get the input and output streams, using temp objects because
            // member streams are final
            try {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) {
            }

            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }

        @Override
        public void run() {
            byte[] buffer = new byte[1024];  // buffer store for the stream
            int bytes; // bytes returned from read()
            // Keep listening to the InputStream until an exception occurs
            while (true) {
                try {
                    // Read from the InputStream
                    bytes = mmInStream.available();
                    if (bytes != 0) {
                        buffer = new byte[1024];
                        SystemClock.sleep(100); //pause and wait for rest of data. Adjust this depending on your sending speed.
                        bytes = mmInStream.available(); // how many bytes are ready to be read?
                        bytes = mmInStream.read(buffer, 0, bytes); // record how many bytes we actually read
                    }
                } catch (IOException e) {
                    e.printStackTrace();

                    break;
                }
            }
        }

        /* Call this from the main activity to send data to the remote device */
        public void write(String input) {
            byte[] bytes = input.getBytes();           //converts entered String into bytes
            try {
                mmOutStream.write(bytes);
            } catch (IOException e) {
            }
        }

        /* Call this from the main activity to shutdown the connection */
        public void cancel() {
            try {
                mmSocket.close();
            } catch (IOException e) {
            }
        }
    }
}