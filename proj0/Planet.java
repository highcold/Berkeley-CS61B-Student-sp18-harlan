public class Planet {
    double xxPos;
    double yyPos;
    double xxVel;
    double yyVel;
    double mass;
    String imgFileName;
    static final private double  G = 6.67e-11;

    public Planet(double xP, double yP, double xV, double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p){
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p){
        double dx = this.xxPos - p.xxPos;
        double dy = this.yyPos - p.yyPos;
        return Math.sqrt(dx*dx + dy*dy);
    }

    public double calcForceExertedBy(Planet p){
        double r = this.calcDistance(p);
        double f = (G * this.mass * p.mass) / (r*r);

        return f;
    }

    public double calcForceExertedByX(Planet p){
        double f = this.calcForceExertedBy(p);
        double r = this.calcDistance(p);
        double dx = p.xxPos - this.xxPos;

        return (f * dx) / r;
    }

    public double calcForceExertedByY(Planet p){
        double f = this.calcForceExertedBy(p);
        double r = this.calcDistance(p);
        double dy = p.yyPos - this.yyPos;

        return (f * dy) / r;
    }

    public double calcNetForceExertedByX(Planet [] ps){
        double netForceX=0;
        for (Planet p : ps){
            if (this.equals(p)){
                continue;
            }else{
                netForceX += this.calcForceExertedByX(p);
            }
        }
        return netForceX;
    }

    public double calcNetForceExertedByY(Planet [] ps){
        double netForceY=0;
        for (Planet p : ps){
            if (this.equals(p)){
                continue;
            }else{
                netForceY += this.calcForceExertedByY(p);
            }
        }
        return netForceY;
    }

    public void update(double dt, double fX, double fY){
        double aX = fX / this.mass;
        double aY = fY / this.mass;
        double vX = this.xxVel + dt*aX;
        double vY = this.yyVel + dt*aY;
        double Px = this.xxPos + vX*dt;
        double Py = this.yyPos + vY*dt;

        this.xxVel =vX;
        this.yyVel =vY;
        this.xxPos = Px;
        this.yyPos = Py;
    }
    public void draw(){
        StdDraw.picture(this.xxPos, this.yyPos,"images/"+this.imgFileName );
    }

}