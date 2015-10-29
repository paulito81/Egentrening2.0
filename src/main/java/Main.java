import console.UserArrayTester;
import console.UserH2Tester;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;

/**
 * Created by Paul on 21.10.2015.
 */
public class Main {
    @Inject
    private UserArrayTester serviceArray;

    @Inject
    private UserH2Tester serviceH2;

    public void callService() {
        serviceArray.execute();
        serviceH2.execute();
    }

    public static void main(String[] args) {

        WeldContainer container = new Weld().initialize();
        Instance<Main> main = container.instance().select(Main.class);
        main.get().callService();
        container.destroy(main);
        container.close();
    }
}
