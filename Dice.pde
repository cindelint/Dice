int hand[] = new int[13];
int cardIndex;
String suitNames[] = new String[] {"diamond", "club", "heart", "spade"};


void setup() {
	size(750,300);
	rectMode(CENTER);
	textAlign(CENTER,CENTER);
	noLoop();
}

void draw() {
	background(22, 79, 11);

	cardIndex = 0;
	// for (int y=0; y<4; y++) {
		for (int x=0; x<13; x++) {
			Card one = new Card(55*x+45, 100);
			one.show();
		}
	// }
	searchFlush(3, 200);
}

void mousePressed() {
	redraw();
}

boolean search(int x) { //true if it exists within hand
		boolean exist = false;
		for (int i=0; i<cardIndex; i++) {
			if (hand[i] == x) {
				exist = true;
			}
		}
		return exist;
}

void searchFlush(int x, float yPos) {
	int flushV[] = new int[13];
	int count = 0;
	for (int i=0; i<cardIndex; i++) {
		if (hand[i] % 10 == x) {
			flushV[count] = hand[i];
			count++;
		}
	}

	fill(0);
	textSize(13);
	if (count >= 5) {
		text(suitNames[x-1] + " flush exists", 375, yPos);
		for (int i=0; i<count; i++) {
				drawCard(415-count*25+45*i,yPos+40,40,flushV[i]);
		}
	} else {
		text(suitNames[x-1] + " flush doesn't exist", 375, yPos+40);
	}
}

class Card {
	int num;
	int suit; // 1 - diamond, 2 - clover, 3 - heart, 4 - spade
	int cardV;
	int myX, myY;
	float cardW = 50;

	Card (int x, int y) {
		myX = x;
		myY = y;
		num = (int) (Math.random() * 13 + 1);
		suit = (int) (Math.random() * 4 + 1);
		cardV = num*10 + suit;
		while (search(cardV)) {
			//if card alr exists, rerandomize
			num = (int) (Math.random() * 13 + 1);
			suit = (int) (Math.random() * 4 + 1);
			cardV = num*10+suit;
		}
		hand[cardIndex] = cardV;
		cardIndex++;
	}
	void show() {
		drawCard(myX, myY, cardW, cardV);
	}
}

void drawCard(float x, float y, float w, int c) {
	int cNum = (int) (c/10);
	fill(230);
	stroke(0);
	rect(x, y, w, w*1.4, w/7);
	fill(10);
	String nonnum = "0";
	if (cNum > 1 && cNum < 11) {
		nonnum = "" + cNum + "";
	} else if (cNum == 1) {
		nonnum = "A";
	} else if (cNum == 11) {
		nonnum = "J";
	} else if (cNum == 12) {
		nonnum = "Q";
	} else if (cNum == 13) {
		nonnum = "K";
	}

	noStroke();
	if (c%10 == 1 || c%10 == 3) {
		fill(255,0,0);
		if (c%10 == 1) {
			quad(x-w/6, y, x, y-w/4, x+w/6, y, x, y+w/4);
		}
		if (c%10 == 3) {
			ellipse(x-w/12, y-w/20,w/5,w/5);
			ellipse(x+w/12, y-w/20,w/5,w/5);
			triangle(x-w/5, y-w/15, x+w/5, y-w/15, x, y+w/4.5);
		}
	} else {
		fill(0);
		if (c%10 == 2) {
			ellipse(x, y-w/9, w/5, w/5);
			ellipse(x-w/10, y, w/5, w/5);
			ellipse(x+w/10, y, w/5, w/5);
			rect(x, y+w/10, w/15, w/5);
		}
		if (c%10 == 4) {
			ellipse(x-w/12, y+w/20,w/5,w/5);
			ellipse(x+w/12, y+w/20,w/5,w/5);
			triangle(x-w/5, y+w/15, x+w/5, y+w/15, x, y-w/4.5);
			rect(x, y+w/8, w/15, w/5);
		}
	}

	text(nonnum, x - w/3.1, y - w / 2);
	pushMatrix();
	translate(x, y);
	rotate(PI);
	translate(-x, -y);
	text(nonnum, x - w/3.1, y - w / 2);
	popMatrix();
}


/* NORMAL DICE PROGRAM

 int diceTotal;

void setup() {
	size(300,300);
	noLoop();
	rectMode(CENTER);
	textAlign(CENTER, CENTER);
}

void draw() {
	background(200);
	diceTotal = 0;

	for (int j=0; j<4; j++) {
		for (int i=0; i<5; i++) {
			Die bobert = new Die(i*55+40,j*55+50);
			bobert.show();
		}
	}
	text("Your dice roll is: " + diceTotal, 150, 275);
}

void mousePressed() {
	redraw();
}

class Die //models one single dice cube
{
	//variable declarations here
	int myX, myY;
	int result;
	int size = 40;
	int dot = size/10;
	Die(int x, int y) {
		//variable initializations here (constructor)
		myX = x;
		myY = y;
	}
	void roll() {
		result = (int) (Math.random() * 6 + 1);
		diceTotal += result;
	}

	void dots(float x, float y) {
		ellipse(myX+size*x, myY+size*y, dot, dot);
	}
	void show() {
		rectMode(CENTER);
		fill(#9ca7ba);
		rect(myX,myY,size,size,size/5);

		roll();
		fill(0);

		if (result == 1) {
			ellipse(myX,myY,dot,dot);
		} else if (result == 2) {
			ellipse(myX-size/4,myY-size/4,dot,dot);
			ellipse(myX+size/4,myY+size/4,dot,dot);
		} else if (result == 3) {
			ellipse(myX-size/3,myY-size/3,dot,dot);
			ellipse(myX,myY,dot,dot);
			ellipse(myX+size/3,myY+size/3,dot,dot);
		} else if (result == 4) {
			ellipse(myX-size/4,myY-size/4,dot,dot);
			ellipse(myX+size/4,myY+size/4,dot,dot);
			ellipse(myX-size/4,myY+size/4,dot,dot);
			ellipse(myX+size/4,myY-size/4,dot,dot);
		} else if (result == 5) {
			ellipse(myX-size/3,myY-size/3,dot,dot);
			ellipse(myX,myY,dot,dot);
			ellipse(myX+size/3,myY+size/3,dot,dot);
			ellipse(myX-size/3,myY+size/3,dot,dot);
			ellipse(myX+size/3,myY-size/3,dot,dot);
		} else if (result == 6) {
			ellipse(myX-size/3,myY-size/3,dot,dot);
			ellipse(myX-size/3,myY,dot,dot);
			ellipse(myX+size/3,myY+size/3,dot,dot);
			ellipse(myX-size/3,myY+size/3,dot,dot);
			ellipse(myX+size/3,myY,dot,dot);
			ellipse(myX+size/3,myY-size/3,dot,dot);
		}
	}
}

*/
