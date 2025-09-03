package pt.isec.gps2324.gps_g14.ConsoleApp;

public abstract class ConsoleAppModule implements IConsoleAppModule{
    private final String ModuleName;

    public ConsoleAppModule(String ModuleName) {
        this.ModuleName = ModuleName;
    }

    public final void help() {
        System.out.printf("--- Menu: %s ---\n", this.ModuleName);
        this._help();
    }
}
