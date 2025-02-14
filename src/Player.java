import java.util.List;

public class Player {
    private int currentGems;
    private int gemsPerBanner;
    private int gemsPerPull;
    private int guaranteePullCount;
    private List<Character> desiredCharacters;

    public Player(int currentGems, int gemsPerBanner, int gemsPerPull, int guaranteePullCount, List<Character> desiredCharacters) {
        this.currentGems = currentGems;
        this.gemsPerBanner = gemsPerBanner;
        this.gemsPerPull = gemsPerPull;
        this.guaranteePullCount = guaranteePullCount;
        this.desiredCharacters = desiredCharacters;
    }

    public int getCurrentGems() {
        return currentGems;
    }

    public int getGemsPerBanner() {
        return gemsPerBanner;
    }

    public int getGemsPerPull() {
        return gemsPerPull;
    }

    public int getGuaranteePullCount() {
        return guaranteePullCount;
    }

    public List<Character> getDesiredCharacters() {
        return desiredCharacters;
    }

    // Calculate available pulls based on current gems and pull cost
    public int calculateAvailablePulls() {
        return currentGems / gemsPerPull;
    }
}
