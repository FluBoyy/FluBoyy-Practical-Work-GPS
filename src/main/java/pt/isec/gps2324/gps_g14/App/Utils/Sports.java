package pt.isec.gps2324.gps_g14.App.Utils;

public enum Sports {
    VOLEIBOL, FUTEBOL, ANDEBOL, BASQUET;

    public int getDuration() {
        switch(this) {
            default:
                return 120;
          }
       }
}
