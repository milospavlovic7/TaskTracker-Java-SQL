package thread;

import controller.ServerController;
import domain.*;
import transfer.Request;
import transfer.Response;
import transfer.util.ResponseStatus;
import transfer.util.Operation;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ThreadClient extends Thread {

    private Socket socket;

    ThreadClient(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            while (!isInterrupted()) {
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                Request request = (Request) in.readObject();
                Response response = handleRequest(request);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Response handleRequest(Request request) {
        Response response = new Response(null, null, ResponseStatus.SUCCESS);
        try {
            switch (request.getOperation()) {

                // Login
                case Operation.LOGIN_ZAPOSLENI:
                    Zaposleni zaposleni = (Zaposleni) request.getData();
                    response.setData(ServerController.getInstance().loginZaposleni(zaposleni));
                    break;

                case Operation.LOGIN_MENADZER:
                    Menadzer menadzer = (Menadzer) request.getData();
                    response.setData(ServerController.getInstance().loginMenadzer(menadzer));
                    break;

                // Zaposleni
                case Operation.ADD_ZAPOSLENI:
                    ServerController.getInstance().addZaposleni((Zaposleni) request.getData());
                    break;

                case Operation.GET_ZAPOSLENI:
                    response.setData(ServerController.getInstance().getZaposleni((Zaposleni) request.getData()));
                    break;

                case Operation.UPDATE_ZAPOSLENI:
                    ServerController.getInstance().updateZaposleni((Zaposleni) request.getData());
                    break;

                case Operation.DELETE_ZAPOSLENI:
                    ServerController.getInstance().deleteZaposleni((Zaposleni) request.getData());
                    break;

                case Operation.GET_ALL_ZAPOSLENI:
                    response.setData(ServerController.getInstance().getAllZaposleni());
                    break;

                // Zadatak
                case Operation.ADD_ZADATAK:
                    ServerController.getInstance().addZadatak((Zadatak) request.getData());
                    break;

                case Operation.GET_ZADATAK:
                    response.setData(ServerController.getInstance().getZadatak((Zadatak) request.getData()));
                    break;

                case Operation.UPDATE_ZADATAK:
                    ServerController.getInstance().updateZadatak((Zadatak) request.getData());
                    break;

                case Operation.DELETE_ZADATAK:
                    ServerController.getInstance().deleteZadatak((Zadatak) request.getData());
                    break;

                case Operation.GET_ALL_ZADATAK:
                    response.setData(ServerController.getInstance().getAllZadatak());
                    break;

                // Posao
                case Operation.ADD_POSAO:
                    ServerController.getInstance().addPosao((Posao) request.getData());
                    break;

                case Operation.GET_POSAO:
                    response.setData(ServerController.getInstance().getPosao((Posao) request.getData()));
                    break;

                case Operation.UPDATE_POSAO:
                    ServerController.getInstance().updatePosao((Posao) request.getData());
                    break;

                case Operation.DELETE_POSAO:
                    ServerController.getInstance().deletePosao((Posao) request.getData());
                    break;

                case Operation.GET_ALL_POSAO:
                    response.setData(ServerController.getInstance().getAllPosao());
                    break;

                // Menadzer
                case Operation.ADD_MENADZER:
                    ServerController.getInstance().addMenadzer((Menadzer) request.getData());
                    break;

                case Operation.GET_MENADZER:
                    response.setData(ServerController.getInstance().getMenadzer((Menadzer) request.getData()));
                    break;

                case Operation.UPDATE_MENADZER:
                    ServerController.getInstance().updateMenadzer((Menadzer) request.getData());
                    break;

                case Operation.DELETE_MENADZER:
                    ServerController.getInstance().deleteMenadzer((Menadzer) request.getData());
                    break;

                case Operation.GET_ALL_MENADZER:
                    response.setData(ServerController.getInstance().getAllMenadzer());
                    break;

                // Zaposlenje
                case Operation.ADD_ZAPOSLENJE:
                    ServerController.getInstance().addZaposlenje((Zaposlenje) request.getData());
                    break;

                case Operation.DELETE_ZAPOSLENJE:
                    ServerController.getInstance().deleteZaposlenje((Zaposlenje) request.getData());
                    break;

                case Operation.GET_ALL_ZAPOSLENJE:
                    response.setData(ServerController.getInstance().getAllZaposlenje());
                    break;

                // PripadnostZP
                case Operation.ADD_PRIPADNOSTZP:
                    ServerController.getInstance().addPripadnostZP((PripadnostZadatkaPoslu) request.getData());
                    break;

                case Operation.DELETE_PRIPADNOSTZP:
                    ServerController.getInstance().deletePripadnostZP((PripadnostZadatkaPoslu) request.getData());
                    break;

                case Operation.GET_ALL_PRIPADNOSTZP:
                    response.setData(ServerController.getInstance().getAllPripadnostZP());
                    break;

                default:
                    response.setResponseStatus(ResponseStatus.ERROR);
                    response.setException(new Exception("Nepoznata operacija"));
                    break;
            }
        } catch (Exception e) {
            response.setResponseStatus(ResponseStatus.ERROR);
            response.setException(e);
        }
        return response;
    }
}
