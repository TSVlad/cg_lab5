import com.jogamp.common.nio.Buffers;
import com.jogamp.opengl.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.IntBuffer;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MainGLEventsListener implements GLEventListener {
    private double timeStart;
    private double time;
    private int shaderProgram;

    public void init(GLAutoDrawable glAutoDrawable) {
        GL2 gl = glAutoDrawable.getGL().getGL2();

        gl.glClearColor(0, 0, 0, 0);
        gl.glMatrixMode(gl.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrtho(0, 1, 0, 1, -1, 1);

        gl.glMatrixMode(gl.GL_MODELVIEW);
        gl.glLoadIdentity();

        setupShaders(gl);
        noFormat = new DecimalFormat("0.00");
//        GL2 gl = glAutoDrawable.getGL().getGL2();
//
//        int v = gl.glCreateShader(GL2.GL_VERTEX_SHADER);
//        String code = "";
//        try (BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/vertex_shader.glsl")))){
//            String line = "";
//            while ((line = reader.readLine()) != null) {
//                code += line;
//            }
//        } catch (FileNotFoundException e) {
//            System.out.println("File not found");
//        } catch (IOException e) {
//            System.out.println(e.toString());
//        }
//
//        gl.glShaderSource(v, 1, new String[]{code}, null);
//        gl.glCompileShader(v);
//        shaderProgram = gl.glCreateProgram();
//        gl.glAttachShader(shaderProgram, v);
//        gl.glLinkProgram(shaderProgram);
//        gl.glValidateProgram(shaderProgram);
//        gl.glUseProgram(shaderProgram);
//        timeStart = new Date().getTime();
    }

    public void dispose(GLAutoDrawable glAutoDrawable) {

    }

    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);

//        gl.glColor3d(1, 0, 0);
//        gl.glBegin(GL2.GL_POLYGON);
//        gl.glVertex3d(0.8, 0.5, 0.5);
//        gl.glVertex3d(0.8, 0, 0);
//        gl.glVertex3d(-0.3, -0.5, 0);
//        gl.glVertex3d(-0.3, 0, 0.5);
//        gl.glEnd();
//
//        System.out.println(time);
//        time = (new Date().getTime() - timeStart) / 1000;
//        gl.glUseProgram(shaderProgram);
//        int t = gl.glGetUniformLocation(shaderProgram, "t");
//        gl.glUniform4f(t, 0.0f, (float) time, 0.0f, 1.0f);

        gl.glColor3f(1, 0, 0);
        gl.glBegin(gl.GL_LINES);
        gl.glVertex2d(0, -d);
        gl.glVertex2d(0, d);
        gl.glEnd();

//        gl.glBindBuffer(gl.GL_ARRAY_BUFFER, 0);

//        final IntBuffer data = Buffers.newDirectIntBuffer(2 * 3);
//        data.put(0, 0);
//        data.put(1, 1);
//        data.put(2, 2);
//        data.put(3, 0);
//        data.put(4, 2);
//        data.put(5, 3);
//        final IntBuffer buffers = Buffers.newDirectIntBuffer(1);
//        g.glGenBuffers(1, buffers);
//        final int id = buffers.get(0);
//        g.glBindBuffer(GL.GL_ELEMENT_ARRAY_BUFFER, id);
//        g
//                .glBufferData(
//                        GL.GL_ELEMENT_ARRAY_BUFFER,
//                        (long) (6 * 4),
//                        data,
//                        GL.GL_STATIC_DRAW);
//        g.glBindBuffer(GL.GL_ELEMENT_ARRAY_BUFFER, 0);

        updateFPS();


//
//        int texAttrib = gl.glGetAttribLocation(shaderProgram, "texcoord");
//        gl.glEnableVertexAttribArray(texAttrib);
//        gl.glVertexAttribPointer(texAttrib, 2, gl.GL_FLOAT, gl.GL_FALSE, 4, 2);

//        int[] vboIds = new int [2];
//        gl.glGenBuffers(2, IntBuffer.wrap(vboIds));
//
//// Load positions.
//        gl.glBindBuffer(gl.GL_ARRAY_BUFFER, vboIds[0]);
//        gl.glBufferData(gl.GL_ARRAY_BUFFER, sizeof(positions), positions, gl.GL_STATIC_DRAW);
//
//// Load texture coordinates.
//        gl.glBindBuffer(gl.GL_ARRAY_BUFFER, vboIds[1]);
//        gl.glBufferData(gl.GL_ARRAY_BUFFER, sizeof(texCoords), texCoords, gl.GL_DYNAMIC_DRAW);
    }

    int d;
    int w;
    int h;

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL2 gl = drawable.getGL().getGL2();

        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(gl.GL_PROJECTION);
        gl.glLoadIdentity();

        w = width / 2;
        h = height / 2;
        gl.glOrtho(-1 * w, w, -1 * h, h, -1000, 1000);
        gl.glMatrixMode(gl.GL_MODELVIEW);
        gl.glLoadIdentity();
        d = Math.min(w, h);
    }

    public void animate() {

    }

    private double stTime;
    private double enTime;
    private double frameCt;
    private NumberFormat noFormat;

    private void updateFPS() {
        enTime = System.currentTimeMillis();
        double diff = enTime - stTime;
        frameCt++;
        if (diff > 1000) {
            double fps = frameCt * 1000 / diff;
            stTime = enTime;
            String s = noFormat.format(fps);
            System.out.println("Name [FPS: " + s + "]");
            frameCt = 0;
        }
    }

    int p;
    int v;
    int g;

    private void setupShaders(GL2 gl) {
        v = gl.glCreateShader(gl.GL_VERTEX_SHADER);
        g = gl.glCreateShader(GL3.GL_GEOMETRY_SHADER);

        String[] vs = new String[1];
        vs[0] = readShaderFile("/vert_shader.vert");
        String[] gs = new String[1];
        gs[0] = readShaderFile("/geom_shader.geom");

        int[] vb = new int[] { vs[0].length() };
        gl.glShaderSource(v, 1, vs, vb, 0);

        int[] gb = new int[] { gs[0].length() };
        gl.glShaderSource(g, 1, gs, gb, 0);

        gl.glCompileShader(v);
        gl.glCompileShader(g);

        p = gl.glCreateProgram();

        gl.glAttachShader(p, v);
        gl.glAttachShader(p, g);

        gl.glProgramParameteri(p, gl.GL_GEOMETRY_INPUT_TYPE_EXT,
                        gl.GL_LINES);
        gl.glProgramParameteri(p, gl.GL_GEOMETRY_OUTPUT_TYPE_EXT,
                gl.GL_LINE_STRIP);

        int[] temp = new int[2];
        gl.glGetIntegerv(0x8DE0, temp, 0);
        gl.glProgramParameteri(p, gl.GL_GEOMETRY_VERTICES_OUT_EXT, temp[0]);

        gl.glLinkProgram(p);
        gl.glValidateProgram(p);
        gl.glUseProgram(p);
    }

    private String readShaderFile(String file) {
        String s = "";
        try {
            BufferedReader r = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(file)));
            String t = r.readLine();
            while (t != null) {
                s += t;
                s += "\n";
                t = r.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }
}
