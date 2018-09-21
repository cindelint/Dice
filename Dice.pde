

void setup()
{
	size(300,300);
	noLoop();
	rectMode(CENTER);
}

void draw()
{
	//your code here
	for (int i=0; i<5; i++) {
		Die bobert = new Die(i*55+40,150);
		bobert.show();
	}
}

void mousePressed()
{
	redraw();
}

class Die //models one single dice cube
{
	//variable declarations here
	int myX, myY;
	int result;
	int size = 50;
	int dot = size/10;
	Die(int x, int y) //constructor
	{
		//variable initializations here
		myX = x;
		myY = y;
	}
	void roll()
	{
		result = (int) (Math.random() * 6 + 1);
	}
	void show()
	{
		//your code here
		rectMode(CENTER);
		fill(#9ca7ba);
		rect(myX,myY,size,size,size/5);

		roll();
		fill(0);
		void dots(int x, int y) {
			ellipse(myX+size/x, myY+size/y,dot,dot);
		}	
		if (result == 1) {
			dots(0,0);
		} else if (result == 2) {
			dots(-4,-4);
			dots(4,4)
		} else if (result == 3) {
			dots(-3,-3);
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

