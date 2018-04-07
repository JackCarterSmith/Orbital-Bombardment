package fr.jackcartersmith.orbsat.common.interfaces;

public class PhotonRecieving extends PhotonProducing{
    public void recievePhotons(int power)
    {
        this.currentCharge += power;
    }
}
