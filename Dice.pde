Die bobert = new Die(150,150);

void setup()
{
	size(300,300);
	noLoop();
}

void draw()
{
	//your code here
	bobert.show();
}

void mousePressed()
{
	redraw();
}

class Die //models one single dice cube
{
	//variable declarations here
	int myX, myY;
	Die(int x, int y) //constructor
	{
		//variable initializations here
		myX = x;
		myY = y;
	}
	void roll()
	{
		//your code here
	}
	void show()
	{
		//your code here
		rectMode(CENTER);
		fill(#9ca7ba);
		rect(myX,myY,50,50,10);
	}
}
