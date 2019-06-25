public class NBody {
    public static double readRadius(String path){
        In in = new In(path);

        /* Every time you call a read method from the In class,
         * it reads the next thing from the file, assuming it is
         * of the specified type. */

        /* Compare the calls below to the contents of BasicInDemo_input_file.txt */

        double firstItemInFile = in.readDouble();
        double secondItemInFile = in.readDouble();

        return  secondItemInFile;
    }

    public static Planet[] readPlanets(String path){
        In in = new In(path);
        int size = in.readInt();
        double radius = in.readDouble();
        Planet[] ps = new Planet[size];
        for (int i = 0; i < size; i++) {
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();

            Planet p = new Planet(xxPos, yyPos, xxVel,
                    yyVel, mass, imgFileName);

            ps[i] = p;
        }

        return ps;

    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double time = 0;
        double radius = readRadius(filename);
        Planet[] ps = readPlanets(filename);
        int length = ps.length;




        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        StdDraw.picture(0, 0,"images/starfield.jpg" );
        for (int i = 0; i < length; i++) {
            ps[i].draw();
        }
        while (time <= T){
            double[] xForce = new double[ps.length];
            double[] yForce = new double[ps.length];
            for (int i = 0; i < length; i++) {
                Planet p = ps[i];
                xForce[i] = p.calcNetForceExertedByX(ps);
                yForce[i] = p.calcNetForceExertedByY(ps);
            }
            for (int i = 0; i < length; i++) {
                ps[i].update(dt,xForce[i],yForce[i]);
            }
            StdDraw.picture(0, 0,"images/starfield.jpg" );
            for (int i = 0; i < length; i++) {
                ps[i].draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            time += dt;

        }

        StdDraw.show();

        StdOut.printf("%d\n", length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < length; i += 1) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    ps[i].xxPos, ps[i].yyPos, ps[i].xxVel,
                    ps[i].yyVel, ps[i].mass, ps[i].imgFileName);
        }

    }
}