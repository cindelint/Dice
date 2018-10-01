import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Dice extends PApplet {

int hand[] = new int[13];
int cardIndex;


public void setup() {
	
	rectMode(CENTER);
	textAlign(CENTER,CENTER);
	noLoop();

}

public void draw() {
	background(22, 79, 11);

	cardIndex = 0;
	// for (int y=0; y<4; y++) {
		for (int x=0; x<13; x++) {
			Card one = new Card(55*x+45, 100);
			one.show();
		}
	// }

	searchFlush(2);
}

public void mousePressed() {
	redraw();
}

public boolean search(int x) { //true if it exists within hand
		boolean exist = false;
		for (int i=0; i<cardIndex; i++) {
			if (hand[i] == x) {
				exist = true;
			}
		}
		return exist;
}

public void searchFlush(int x) {
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
		text("club flush exists", 375, 250);
		for (int i=0; i<count; i++) {
			drawCard(270+45*i,300,40,flushV[i]);
		}
	} else {
		text("club flush doesn't exist", 375, 325);
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
		cardV = num*10+suit;
		while (search(cardV)) {
			//if card alr exists, rerandomize
			num = (int) (Math.random() * 13 + 1);
			suit = (int) (Math.random() * 4 + 1);
			cardV = num*10+suit;;
		}
		hand[cardIndex] = cardV;
		cardIndex++;
	}
	public void show() {
		drawCard(myX, myY, cardW, cardV);
	}
}

public void drawCard(float x, float y, float w, int card) {
	fill(230);
	stroke(0);
	rect(x, y, w, w*1.4f, w/7);
	fill(10);
	String nonnum;
	if (card/10 > 1 && card/10 < 11) {
		nonnum = Integer.toString(card/10);
	} else if (card/10 == 1) {
		nonnum = "A";
	} else if (card/10 == 11) {
		nonnum = "J";
	} else if (card/10 == 12) {
		nonnum = "Q";
	} else /* (card/10 == 13)*/ {
		nonnum = "K";
	}

	noStroke();
	if (card%10 == 1 || card%10 == 3) {
		fill(255,0,0);
		if (card%10 == 1) {
			quad(x-w/6, y, x, y-w/4, x+w/6, y, x, y+w/4);
		}
		if (card%10 == 3) {
			ellipse(x-w/12, y-w/20,w/5,w/5);
			ellipse(x+w/12, y-w/20,w/5,w/5);
			triangle(x-w/5, y-w/15, x+w/5, y-w/15, x, y+w/4.5f);
		}
	} else {
		fill(0);
		if (card%10 == 2) {
			ellipse(x, y-w/9, w/5, w/5);
			ellipse(x-w/10, y, w/5, w/5);
			ellipse(x+w/10, y, w/5, w/5);
			rect(x, y+w/10, w/15, w/5);
		}
		if (card%10 == 4) {
			ellipse(x-w/12, y+w/20,w/5,w/5);
			ellipse(x+w/12, y+w/20,w/5,w/5);
			triangle(x-w/5, y+w/15, x+w/5, y+w/15, x, y-w/4.5f);
			rect(x, y+w/8, w/15, w/5);
		}
	}

	text(nonnum, x - w/3.1f, y - w / 2);
	pushMatrix();
	translate(x, y);
	rotate(PI);
	translate(-x, -y);
	text(nonnum, x - w/3.1f, y - w / 2);
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
  public void settings() { 	size(750,350); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Dice" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
