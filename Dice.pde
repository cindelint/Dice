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