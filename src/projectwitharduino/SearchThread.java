package projectwitharduino;

public class SearchThread extends Thread {
    private Manager manager;
    private Target target;

    public SearchThread(Manager manager, Target target) {
        this.manager = manager;
        this.target = target;
    }

    @Override
    public void run() {
        manager.Search(target);
    }
}
