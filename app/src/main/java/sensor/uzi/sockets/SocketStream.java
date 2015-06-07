package sensor.uzi.sockets;
/** |SocketStream.java|
  En esta clase se establece el esquema para enviar atravez de protocolo sockect los datos extraidos
  del sensor los cuales son obtenidos por la clase "SensorTagData.java", estos datos se envian de forma
  asincorna con el Metodo AsyncTask.
*
* [TODO]
*   - Definir el tipo dato a enviar por stream.
*   - Implemtar un metodo para generar latencia.
*   - Generar JSON Object para multiples lecturas.
*               Con campos : + ID del telefono
*                          : + Temp amb y obj
*                          : + Hum
*                          : + Coordenadas
* Julio César
* @uzi200
* 2014
**/

import java.io.DataOutputStream;
import java.net.Socket;
import android.os.AsyncTask;
import android.util.Log;

public class SocketStream extends AsyncTask<Double, Void, Void> {

    Socket clientSocket;
    DataOutputStream outToServer;

    private static final String IP = "192.168.0.16";
    private static final int PORT = 8000;
    private static final String TAG = "SocketStream";

    @Override
    protected Void doInBackground(Double... params) {

        try {

            clientSocket = new Socket(IP, PORT);

            if (clientSocket.isConnected()) {

                Log.i(TAG, "Connected to Nodejs");
                outToServer = new DataOutputStream(
                clientSocket.getOutputStream());

                /*Test stream thread
                Random r = new Random();*/

                while (clientSocket.isConnected()) {

                /* double out = MainActivity.JAJA;
                byte[] buf = out.getBytes("UTF-8");*/

                String out = Double.toString(MainActivity.JAJA);
                outToServer.writeUTF(out);
                //outToServer.writeDouble(out);
                Thread.sleep(1000);

                }
            }
        } catch (Exception e) {
            Log.e(TAG, "Server 404 !");
            Log.e(TAG, "" + e);
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void result) {

       /*Toast.makeText(mContext, "", Toast.LENGTH_SHORT)
                .show();*/
        super.onPostExecute(result);
    }
}
