package controller;

import domain.*;
import session.Session;
import transfer.Request;
import transfer.Response;
import transfer.util.Operation;
import transfer.util.ResponseStatus;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ClientController {

    private static ClientController instance;
    private AbstractDomainObject ulogovani;

    private ClientController() {}

    public static ClientController getInstance() {
        if (instance == null) {
            instance = new ClientController();
        }
        return instance;
    }

    // --- LOGIN ---
    public Zaposleni login(Zaposleni zaposleni) throws Exception {
        return (Zaposleni) sendRequest(Operation.LOGIN_ZAPOSLENI, zaposleni);
    }

    public Menadzer loginMenadzer(Menadzer menadzer) throws Exception {
        return (Menadzer) sendRequest(Operation.LOGIN_MENADZER, menadzer);
    }

    // --- ZAPOSLENI ---
    public Zaposleni addZaposleni(Zaposleni zaposleni) throws Exception {
        return (Zaposleni) sendRequest(Operation.ADD_ZAPOSLENI, zaposleni);
    }

    public Zaposleni getZaposleni(Zaposleni zaposleni) throws Exception {
        return (Zaposleni) sendRequest(Operation.GET_ZAPOSLENI, zaposleni);
    }

    public void updateZaposleni(Zaposleni zaposleni) throws Exception {
        sendRequest(Operation.UPDATE_ZAPOSLENI, zaposleni);
    }

    public void deleteZaposleni(Zaposleni zaposleni) throws Exception {
        sendRequest(Operation.DELETE_ZAPOSLENI, zaposleni);
    }

    public ArrayList<Zaposleni> getAllZaposleni() throws Exception {
        return (ArrayList<Zaposleni>) sendRequest(Operation.GET_ALL_ZAPOSLENI, null);
    }

    // --- ZADATAK ---
    public Zadatak addZadatak(Zadatak zadatak) throws Exception {
        return (Zadatak) sendRequest(Operation.ADD_ZADATAK, zadatak);
    }

    public Zadatak getZadatak(Zadatak zadatak) throws Exception {
        return (Zadatak) sendRequest(Operation.GET_ZADATAK, zadatak);
    }

    public void updateZadatak(Zadatak zadatak) throws Exception {
        sendRequest(Operation.UPDATE_ZADATAK, zadatak);
    }

    public void deleteZadatak(Zadatak zadatak) throws Exception {
        sendRequest(Operation.DELETE_ZADATAK, zadatak);
    }

    public ArrayList<Zadatak> getAllZadaci() throws Exception {
        return (ArrayList<Zadatak>) sendRequest(Operation.GET_ALL_ZADATAK, null);
    }

    // --- POSAO ---
    public Posao addPosao(Posao posao) throws Exception {
        return (Posao) sendRequest(Operation.ADD_POSAO, posao);
    }

    public Posao getPosao(Posao posao) throws Exception {
        return (Posao) sendRequest(Operation.GET_POSAO, posao);
    }

    public void updatePosao(Posao posao) throws Exception {
        sendRequest(Operation.UPDATE_POSAO, posao);
    }

    public void deletePosao(Posao posao) throws Exception {
        sendRequest(Operation.DELETE_POSAO, posao);
    }

    public ArrayList<Posao> getAllPoslovi() throws Exception {
        return (ArrayList<Posao>) sendRequest(Operation.GET_ALL_POSAO, null);
    }

    // --- MENADZER ---
    public Menadzer addMenadzer(Menadzer menadzer) throws Exception {
        return (Menadzer) sendRequest(Operation.ADD_MENADZER, menadzer);
    }

    public Menadzer getMenadzer(Menadzer menadzer) throws Exception {
        return (Menadzer) sendRequest(Operation.GET_MENADZER, menadzer);
    }

    public void updateMenadzer(Menadzer menadzer) throws Exception {
        sendRequest(Operation.UPDATE_MENADZER, menadzer);
    }

    public void deleteMenadzer(Menadzer menadzer) throws Exception {
        sendRequest(Operation.DELETE_MENADZER, menadzer);
    }

    public ArrayList<Menadzer> getAllMenadzeri() throws Exception {
        return (ArrayList<Menadzer>) sendRequest(Operation.GET_ALL_MENADZER, null);
    }

    // --- ZAPOSLENJE ---
    public Zaposlenje addZaposlenje(Zaposlenje zaposlenje) throws Exception {
        return (Zaposlenje) sendRequest(Operation.ADD_ZAPOSLENJE, zaposlenje);
    }

    public void deleteZaposlenje(Zaposlenje zaposlenje) throws Exception {
        sendRequest(Operation.DELETE_ZAPOSLENJE, zaposlenje);
    }

    public ArrayList<Zaposlenje> getAllZaposlenja() throws Exception {
        return (ArrayList<Zaposlenje>) sendRequest(Operation.GET_ALL_ZAPOSLENJE, null);
    }

    // --- PRIPADNOST ZADATKA POSLU ---
    public PripadnostZadatkaPoslu addPripadnostZP(PripadnostZadatkaPoslu pripadnostZP) throws Exception {
        return (PripadnostZadatkaPoslu) sendRequest(Operation.ADD_PRIPADNOSTZP, pripadnostZP);
    }

    public void deletePripadnostZP(PripadnostZadatkaPoslu pripadnostZP) throws Exception {
        sendRequest(Operation.DELETE_PRIPADNOSTZP, pripadnostZP);
    }

    public ArrayList<PripadnostZadatkaPoslu> getAllPripadnostiZP() throws Exception {
        return (ArrayList<PripadnostZadatkaPoslu>) sendRequest(Operation.GET_ALL_PRIPADNOSTZP, null);
    }

    // --- UTILITY ---
    private Object sendRequest(int operation, Object data) throws Exception {
        Request request = new Request(operation, data);

        ObjectOutputStream out = new ObjectOutputStream(Session.getInstance().getSocket().getOutputStream());
        out.writeObject(request);

        ObjectInputStream in = new ObjectInputStream(Session.getInstance().getSocket().getInputStream());
        Response response = (Response) in.readObject();

        if (response.getResponseStatus().equals(ResponseStatus.ERROR)) {
            throw response.getException();
        } else {
            return response.getData();
        }
    }

    public void setUlogovani(AbstractDomainObject ulogovani) {
        this.ulogovani = ulogovani;
    }

    public AbstractDomainObject getUlogovani() {
        return ulogovani;
    }
}
