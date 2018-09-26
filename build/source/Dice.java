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

String hand[] = new String[52];
int cardIndex;


public void setup() {
	
	rectMode(CENTER);
	textAlign(CENTER,CENTER);
	noLoop();

}

public void draw() {
	cardIndex = 0;
	for (int y=0; y<4; y++) {
		for (int x=0; x<13; x++) {
			Card one = new Card(55*x+40, y*75+40);
			one.show();
		}
	}
	println(hand);
	println(search());
}

public void mousePressed() {
	redraw();
}

public boolean search() { //true if it exists within hand
		boolean exist = false;
		for (int i=0; i<hand.length; i++) {
			if (hand[i] == "11") {
				exist = true;
			}
		}
		return exist;
}

class Card {
	int num;
	String nonnum; //for displaying the letter values
	int suit; // 1 - diamond, 2 - clover, 3 - heart, 4 - spade
	int myX, myY;
	float cardW = 50;

	Card (int x, int y) {
		myX = x;
		myY = y;
		num = (int) (Math.random() * 13 + 1);
		suit = (int) (Math.random() * 4 + 1);
		hand[cardIndex] = Integer.toString(num) + Integer.toString(suit);
		cardIndex++;
		/* while (search(Integer.toString(num) + Integer.toString(suit))) {
			//if card alr exists, rerandomize
			num = (int) (Math.random() * 13 + 1);
			suit = (int) (Math.random() * 4 + 1);
		} */
	}
	public void show() {
		fill(230);
		stroke(0);
		rect(myX, myY, cardW, cardW*1.4f, cardW/7);
		fill(10);
		if (num > 1 && num < 11) {
			nonnum = Integer.toString(num);
		} else if (num == 1) {
			nonnum = "A";
		} else if (num == 11) {
			nonnum = "J";
		} else if (num == 12) {
			nonnum = "Q";
		} else if (num == 13) {
			nonnum = "K";
		}

		noStroke();
		if (suit == 1 || suit == 3) {
			fill(255,0,0);
			if (suit == 1) {
				quad(myX-cardW/6, myY, myX, myY-cardW/4, myX+cardW/6, myY, myX, myY+cardW/4);
			}
			if (suit == 3) {
				ellipse(myX-cardW/12, myY-cardW/20,cardW/5,cardW/5);
				ellipse(myX+cardW/12, myY-cardW/20,cardW/5,cardW/5);
				triangle(myX-cardW/5, myY-cardW/15, myX+cardW/5, myY-cardW/15, myX, myY+cardW/4.5f);
			}
		} else {
			fill(0);
			if (suit == 2) {
				ellipse(myX, myY-cardW/9, cardW/5, cardW/5);
				ellipse(myX-cardW/10, myY, cardW/5, cardW/5);
				ellipse(myX+cardW/10, myY, cardW/5, cardW/5);
				rect(myX, myY+cardW/10, cardW/15, cardW/5);
			}
			if (suit == 4) {
				ellipse(myX-cardW/12, myY+cardW/20,cardW/5,cardW/5);
				ellipse(myX+cardW/12, myY+cardW/20,cardW/5,cardW/5);
				triangle(myX-cardW/5, myY+cardW/15, myX+cardW/5, myY+cardW/15, myX, myY-cardW/4.5f);
				rect(myX, myY+cardW/8, cardW/15, cardW/5);
			}
		}

		text(nonnum, myX - cardW/3.1f, myY - cardW / 2);
		pushMatrix();
		translate(myX, myY);
		rotate(PI);
		translate(-myX, -myY);
		text(nonnum, myX - cardW/3.1f, myY - cardW / 2);
		popMatrix();
	}
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
