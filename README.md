# SensorSockets_app

Esta app lee via Bluetooth LE un sensor tag de Texas Instruments y envia un stream de datos con sockets a un servidor en Nodejs, [repo del server](https://github.com/juliocesar-io/SensorSockets_server.git).

![Imgur](http://i.imgur.com/cxiKD03.png)


### Clases


La clase `SocketStream.java` hace todo el trabajo de transferencia de forma asincorna, lo primero es crear el socket, `clientSocket = new Socket(IP, PORT);`, alli se tiene que definir la IP y el puerto del servidor que estará recibiendo el `DataOutputStream`.

```java
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
```


Por otro lado la clase `SensorTagData.java` esta encargada de recibir la interfaz Bluetooth del `BluetoothGatt` para extraer la lecturas analogas de los servicios definidos en el `MainActivity.java`.

Activación del servicio en el `MainActivity.java`

```java
/*Temperature Service*/
private static final UUID AMB_TEMP_SERVICE = fromString("f000aa00-0451-4000-b000-000000000000");
```

Obtención de la lectura en `SensorTagData.java`

```java
public static double extractAmbientTemperature(BluetoothGattCharacteristic c) {
    int offset = 2;
    return shortUnsignedAtOffset(c, offset) / 128.0;
}
```
