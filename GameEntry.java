public class GameEntry {
    private String name;
    private int score;

    public GameEntry(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

	@Override
	public String toString() {
		return "GameEntry [name=" + name + ", score=" + score + "]";
	}

   
}

class Scoreboard {
    private int numEntries;
    private GameEntry[] board;

    public Scoreboard(int capacity) {
        board = new GameEntry[capacity];
        numEntries = 0;
    }


public void add(GameEntry e) {
	
	int newScore = e.getScore();
	
	if(numEntries<board.length || newScore>board[numEntries-1].getScore()) {
		if(numEntries<board.length) {
			numEntries++;
		}
		int j=numEntries-1;
		
		while(j>0 && board[j-1].getScore() < newScore) {
			board[j] = board[j-1];
			j--;
		}
		board[j]=e;
	}
}

public GameEntry remove(int i) throws IndexOutOfBoundsException{
	
	if(i<0 || i>=numEntries) {
		throw new IndexOutOfBoundsException("Invalid index: "+i);
	}
	GameEntry temp = board[i];
	
	for(int j=i; j<numEntries-1; j++) {
		board[j] = board[j+1];
	}
	board[numEntries-1]=null;
	numEntries--;
	return temp;
}

public static void main(String[] args) {
	
	Scoreboard scoreboard = new Scoreboard(5);
	
	GameEntry entry1 = new GameEntry("John", 100);
	GameEntry entry2 = new GameEntry("Amy", 200);
	GameEntry entry3 = new GameEntry("Mike", 150);
	
	scoreboard.add(entry1);
	scoreboard.add(entry2);
	scoreboard.add(entry3);
	
	for(int i=0; i<scoreboard.numEntries; i++) {
		System.out.println(scoreboard.board[i]);
	}
	
	scoreboard.remove(1);
	
	for(int i=0; i<scoreboard.numEntries; i++) {
		System.out.println(scoreboard.board[i]);
	}
	
	
}
}