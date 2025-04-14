package controller;

import domain.Menadzer;
import domain.Zaposleni;
import domain.Zadatak;
import domain.Posao;
import domain.Zaposlenje;
import domain.PripadnostZadatkaPoslu;

import java.util.ArrayList;

import so.login.SOLoginMenadzer;
import so.login.SOLoginZaposleni;
import so.menadzer.SOAddMenadzer;
import so.menadzer.SODeleteMenadzer;
import so.menadzer.SOUpdateMenadzer;
import so.menadzer.SOGetMenadzer;
import so.menadzer.SOGetAllMenadzer;
import so.zaposleni.SOAddZaposleni;
import so.zaposleni.SODeleteZaposleni;
import so.zaposleni.SOUpdateZaposleni;
import so.zaposleni.SOGetZaposleni;
import so.zaposleni.SOGetAllZaposleni;
import so.zadatak.SOAddZadatak;
import so.zadatak.SODeleteZadatak;
import so.zadatak.SOUpdateZadatak;
import so.zadatak.SOGetZadatak;
import so.zadatak.SOGetAllZadatak;
import so.posao.SOAddPosao;
import so.posao.SODeletePosao;
import so.posao.SOUpdatePosao;
import so.posao.SOGetPosao;
import so.posao.SOGetAllPosao;
import so.zaposlenja.SOAddZaposlenje;
import so.zaposlenja.SODeleteZaposlenje;
import so.zaposlenja.SOGetAllZaposlenje;
import so.pripadnostZadatkaPoslu.SOAddPripadnostZadatkaPoslu;
import so.pripadnostZadatkaPoslu.SODeletePripadnostZadatkaPoslu;
import so.pripadnostZadatkaPoslu.SOGetAllPripadnostZadatkaPoslu;

public class ServerController {

    private static ServerController instance;

    private ServerController() {}

    public static ServerController getInstance() {
        if (instance == null) {
            instance = new ServerController();
        }
        return instance;
    }

    public Zaposleni loginZaposleni(Zaposleni zaposleni) throws Exception {
        SOLoginZaposleni so = new SOLoginZaposleni();
        so.templateExecute(zaposleni);
        return so.getZaposleni();
    }

    public Menadzer loginMenadzer(Menadzer menadzer) throws Exception {
        SOLoginMenadzer so = new SOLoginMenadzer();
        so.templateExecute(menadzer);
        return so.getMenadzer();
    }

    public void addZaposleni(Zaposleni zaposleni) throws Exception {
        (new SOAddZaposleni()).templateExecute(zaposleni);
    }

    public void deleteZaposleni(Zaposleni zaposleni) throws Exception {
        (new SODeleteZaposleni()).templateExecute(zaposleni);
    }

    public void updateZaposleni(Zaposleni zaposleni) throws Exception {
        (new SOUpdateZaposleni()).templateExecute(zaposleni);
    }

    public Zaposleni getZaposleni(Zaposleni zaposleni) throws Exception {
        SOGetZaposleni so = new SOGetZaposleni();
        so.templateExecute(zaposleni);
        return so.getZaposleni();
    }

    public ArrayList<Zaposleni> getAllZaposleni() throws Exception {
        SOGetAllZaposleni so = new SOGetAllZaposleni();
        so.templateExecute(new Zaposleni());
        return so.getListaZaposlenih();
    }

    public void addZadatak(Zadatak zadatak) throws Exception {
        (new SOAddZadatak()).templateExecute(zadatak);
    }

    public void deleteZadatak(Zadatak zadatak) throws Exception {
        (new SODeleteZadatak()).templateExecute(zadatak);
    }

    public void updateZadatak(Zadatak zadatak) throws Exception {
        (new SOUpdateZadatak()).templateExecute(zadatak);
    }

    public Zadatak getZadatak(Zadatak zadatak) throws Exception {
        SOGetZadatak so = new SOGetZadatak();
        so.templateExecute(zadatak);
        return so.getZadatak();
    }

    public ArrayList<Zadatak> getAllZadatak() throws Exception {
        SOGetAllZadatak so = new SOGetAllZadatak();
        so.templateExecute(new Zadatak());
        return so.getListaZadataka();
    }

    public void addPosao(Posao posao) throws Exception {
        (new SOAddPosao()).templateExecute(posao);
    }

    public void deletePosao(Posao posao) throws Exception {
        (new SODeletePosao()).templateExecute(posao);
    }

    public void updatePosao(Posao posao) throws Exception {
        (new SOUpdatePosao()).templateExecute(posao);
    }

    public Posao getPosao(Posao posao) throws Exception {
        SOGetPosao so = new SOGetPosao();
        so.templateExecute(posao);
        return so.getPosao();
    }

    public ArrayList<Posao> getAllPosao() throws Exception {
        SOGetAllPosao so = new SOGetAllPosao();
        so.templateExecute(new Posao());
        return so.getListaPoslova();
    }

    public Menadzer getMenadzer(Menadzer menadzer) throws Exception {
        SOGetMenadzer so = new SOGetMenadzer();
        so.templateExecute(menadzer);
        return so.getMenadzer();
    }

    public void addMenadzer(Menadzer menadzer) throws Exception {
        (new SOAddMenadzer()).templateExecute(menadzer);
    }

    public void updateMenadzer(Menadzer menadzer) throws Exception {
        (new SOUpdateMenadzer()).templateExecute(menadzer);
    }

    public void deleteMenadzer(Menadzer menadzer) throws Exception {
        (new SODeleteMenadzer()).templateExecute(menadzer);
    }

    public ArrayList<Menadzer> getAllMenadzer() throws Exception {
        SOGetAllMenadzer so = new SOGetAllMenadzer();
        so.templateExecute(new Menadzer());
        return so.getListaMenadzera();
    }

    public void addZaposlenje(Zaposlenje zaposlenje) throws Exception {
        (new SOAddZaposlenje()).templateExecute(zaposlenje);
    }

    public void deleteZaposlenje(Zaposlenje zaposlenje) throws Exception {
        (new SODeleteZaposlenje()).templateExecute(zaposlenje);
    }

    public ArrayList<Zaposlenje> getAllZaposlenje() throws Exception {
        SOGetAllZaposlenje so = new SOGetAllZaposlenje();
        so.templateExecute(new Zaposlenje());
        return so.getListaZaposlenja();
    }

    public void addPripadnostZP(PripadnostZadatkaPoslu pripadnostZP) throws Exception {
        (new SOAddPripadnostZadatkaPoslu()).templateExecute(pripadnostZP);
    }

    public void deletePripadnostZP(PripadnostZadatkaPoslu pripadnostZP) throws Exception {
        (new SODeletePripadnostZadatkaPoslu()).templateExecute(pripadnostZP);
    }

    public ArrayList<PripadnostZadatkaPoslu> getAllPripadnostZP() throws Exception {
        SOGetAllPripadnostZadatkaPoslu so = new SOGetAllPripadnostZadatkaPoslu();
        so.templateExecute(new PripadnostZadatkaPoslu());
        return so.getListaPripadnostiZP();
    }
}
