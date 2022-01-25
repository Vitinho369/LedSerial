package visao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;

import com.fazecast.jSerialComm.SerialPort;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class TelaPortasController implements Initializable{

    @FXML
    private Button btnConectar;

    @FXML
    private ComboBox cbPortas;

    private SerialPort porta;
    private int led = 0;

    @FXML
    void conectar(ActionEvent event) {
        if(btnConectar.getText().equals("Conectar")){
            porta = SerialPort.getCommPort(cbPortas.getSelectionModel().getSelectedItem().toString());
       
            if(porta.openPort()){
                btnConectar.setText("Desconectar");
                cbPortas.setDisable(true);
            }
        }else{
            porta.closePort();
            cbPortas.setDisable(false);
            btnConectar.setText("Conectar");
        }
    }

    @FXML
    void ligarLed(ActionEvent event) {
         
        PrintWriter output = new PrintWriter(porta.getOutputStream());

        if(led==0){
            output.print("1");
            output.flush();
            led = 1;
            
        }else{
            output.print("0");
            output.flush();
            led = 0;
        }
     
            porta.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING, 100, 0);
            BufferedReader in = new BufferedReader(new InputStreamReader(porta.getInputStream()));
            try{
                String msg = in.readLine();
                System.out.println(msg);
            }catch(Exception e){
                System.out.println(e);
            }
     
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
        carregarPortas();
    }

    private void carregarPortas(){
        SerialPort[] nomePortas = SerialPort.getCommPorts();

        for(SerialPort nomePorta: nomePortas)
            cbPortas.getItems().add(nomePorta.getSystemPortName().toString());
    }
}
