package fr.jackcartersmith.ob.interfaces;

public class PhotonRecieving extends PhotonProducing
{
    public void recievePhotons(int power)
    {
        this.currentCharge += power;
    }
}
