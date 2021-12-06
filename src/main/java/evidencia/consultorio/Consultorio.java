
package evidencia.consultorio;

import evidencia.consultorio.*;
import evidencia.consultorio.Doctor;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.Iterator;
import java.util.HashMap;

public class Consultorio {


    public static Scanner leer = new Scanner(System.in);
    public static String outputDoctores = "C:\\Users\\aldop\\Documents\\NetBeansProjects\\EvidenciaJava\\EvidenciaJAVA\\src\\main\\java\\evidencia\\consultorio\\Doctores.csv";
    public static String outputPacientes = "C:\\Users\\aldop\\Documents\\NetBeansProjects\\EvidenciaJava\\EvidenciaJAVA\\src\\main\\java\\evidencia\\consultorio\\Pacientes.csv";
    public static String outputCitas = "C:\\Users\\aldop\\Documents\\NetBeansProjects\\EvidenciaJava\\EvidenciaJAVA\\src\\main\\java\\evidencia\\consultorio\\Citas.csv";
    public static String outputAdministradores = "C:\\Users\\aldop\\Documents\\NetBeansProjects\\EvidenciaJava\\EvidenciaJAVA\\src\\main\\java\\evidencia\\consultorio\\Administradores.csv";


    public static ArrayList <Doctor> aDoc = new ArrayList<Doctor>();
    
    public static ArrayList <Paciente> aPac = new ArrayList<Paciente>(); 
    
    
    public static void main(String[] args) throws IOException, ParseException{
        


            BufferedWriter bwDoctores = new BufferedWriter(new FileWriter(outputDoctores, true));
            BufferedWriter bwPacientes = new BufferedWriter(new FileWriter(outputPacientes, true));
            BufferedWriter bwCitas = new BufferedWriter(new FileWriter(outputCitas, true));
            BufferedWriter bwAdmin = new BufferedWriter(new FileWriter(outputAdministradores, true));

            String line;

            leer.useDelimiter("\n");
            int opc, ban = 0, ban1 = 0;
            boolean acceso = false;
            
            HashMap<String, String> mapa = new HashMap<String, String>();  
            
            System.out.println(" consultorio \n");
            
            do{     
                ban1 = archivV("C:\\Users\\aldop\\Documents\\NetBeansProjects\\EvidenciaJava\\EvidenciaJAVA\\src\\main\\java\\evidencia\\consultorio\\Administradores.csv"); //llamado a metodo que rectifica que el archivo no esté vacio
                if(ban1 == 1)
                {
                    System.out.println("Ingreso solo para administradores\n");
                    System.out.println("Ingresar ID: ");
                    String id = leer.next();
                    System.out.println("Ingresar contraseña: ");
                    String pass = leer.next();
                    
                    load(mapa);  

                    acceso = contrasena(mapa,id,pass);  

                }
                else           
                {    
                    System.out.println("No existen Administradores");
                    System.out.println(" Dar de alta Administrador \n");
                    System.out.println("Ingresar ID: ");
                    String id = leer.next();
                    System.out.println("Ingresar contraseña: ");
                    String pass = leer.next();                    

                    creaAdministrador(mapa, id, pass, bwAdmin); 

                }
                
            }while ( acceso == false ); 
            
                do         
                {
                    try{                                                                    

                    System.out.println("\nSelecciona la opción que se desea:\n");                
                        System.out.println("Dar de alta Administrador  =  1");
                        System.out.println("Dar de alta Doctor =  2");
                        System.out.println("Dar de alta Paciente =  3");
                        System.out.println("Agendar Cita  =  4");
                       
                        System.out.println("Salir  =  0");

                        opc = leer.nextInt();

                        
                        switch(opc)
                        {
                            case 1:

                                System.out.println(" Dar de alta Administrador \n");
                                System.out.println("Ingresae  ID: ");
                                String id = leer.next();
                                System.out.println("Ingresar contraseña: ");
                                String pass = leer.next();                    

                                creaAdministrador(mapa, id, pass, bwAdmin);       

                                break;

                            case 2:


                                creaDoctor(bwDoctores);
                                break;

                            case 3:

                                creaPaciente(bwPacientes);

                                break;

                            case 4:  

                                creaCita(bwCitas);

                                break;
                         
                            case 0:

                                System.out.println("Se ha salido del programa");     
                                ban = 1;                            
                                break;
                            default:
                                System.out.println("Opción no correcta\n");  
                                break;


                        }
                    }
                    catch (Exception e)             
                    {
                        System.out.println("Error \n");
                        break;

                    }
                }while(ban == 0);
        
    }
    

        public static void creaDoctor(BufferedWriter bw) throws IOException {
            
            
        System.out.print("Ingresar nombre del doctor\n");         
        String nombreDoctor = leer.next();
        System.out.print("Ingresar especialidad del doctor\n");
        String especialidadDoctor = leer.next();
        System.out.print("Ingresar Id del doctor\n");
        String idDoctor = leer.next();
        Doctor doctorInfo = new Doctor(idDoctor, nombreDoctor, especialidadDoctor); 

        
        try(FileWriter fw = new FileWriter(outputDoctores, true);   
        BufferedWriter bww = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bww)){


                    out.print(doctorInfo.id);           
                    out.print(",");
                    out.print(doctorInfo.nombreDoctor);
                    out.print(",");
                    out.println(doctorInfo.esp);

                    }
                 catch(IOException e) {
                    System.out.println("IOException catched while writing: " + e.getMessage());
                    } finally {
                        try {
                            if (bw != null) {
                                bw.close();
                                System.out.println("\nCambios guardados");
                            }
                        } catch (IOException e) {
                            System.out.println("IOException catched while closing: " + e.getMessage());
                        }
                    }

    }
    public static void creaPaciente(BufferedWriter bw) throws IOException {

        
        System.out.print("Ingresar nombre del Paciente\n");        
        String nombrePaciente = leer.next();
        System.out.print("Ingresar Id del paciente\n");
        String idPaciente = leer.next();
        Paciente PacienteInfo = new Paciente(nombrePaciente,idPaciente); 

        
        try(FileWriter fw = new FileWriter(outputPacientes, true);   
        BufferedWriter bww = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bww)){


                    out.print(PacienteInfo.id);           
                    out.print(",");
                    out.println(PacienteInfo.nombrePaciente);


                    }
                 catch(IOException e) {
                    System.out.println("IOException catched while writing: " + e.getMessage());
                    } finally {
                        try {
                            if (bw != null) {
                                bw.close();
                                System.out.println("\nCambios guardados");
                            }
                        } catch (IOException e) {
                            System.out.println("IOException catched while closing: " + e.getMessage());
                        }
                    }

    }
    public static void creaCita(BufferedWriter bw) throws IOException {
        
            try{
                System.out.println("Ingresar id de la cita:");
                int idC = leer.nextInt();
                System.out.print("Ingresar fecha de la cita\n");
                String fechaCitaString = leer.next();
                Date fechaCita = new SimpleDateFormat("dd/MM/yyyy").parse(fechaCitaString);
                System.out.print("Ingresar motivo de la cita\n");
                leer.nextLine();
                String motivoCita = leer.nextLine();
                System.out.print("Ingresar id del doctor\n");
                String idDoctor = leer.next();
                System.out.print("Ingresar id del paciente\n");
                String idPaciente = leer.next();
                Cita citaInfo = new Cita(idC, fechaCita, Integer.parseInt(idDoctor), Integer.parseInt(idPaciente), motivoCita);

        
        try(FileWriter fw = new FileWriter(outputCitas, true);   
        BufferedWriter bww = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bww)){


                    out.print(citaInfo.id);           
                    out.print(",");
                    out.println(citaInfo.fechaCita);
                    out.print(",");
                    out.print(citaInfo.idDoctor);
                    out.print(",");
                    out.print(citaInfo.idPaciente);
                    out.print(",");
                    out.println(citaInfo.motivoCita);



                    }
                 catch(IOException e) {
                    System.out.println("IOException catched while writing: " + e.getMessage());
                    } finally {
                        try {
                            if (bw != null) {
                                bw.close();
                                System.out.println("\nCambios guardados");
                            }
                        } catch (IOException e) {
                            System.out.println("IOException catched while closing: " + e.getMessage());
                        }
                    }
                
                
            }
                catch(ParseException e) {
                    System.out.println("ParseException catched while writing: " + e.getMessage());
                    } finally {
                        try {
                            if (bw != null) {
                                bw.close();
                            }
                        } catch (IOException e) {
                            System.out.println("IOException catched while closing: " + e.getMessage());
                        }
                            }
            
                /*try(FileWriter fw = new FileWriter(outputCitas, true);  
                    BufferedWriter bww = new BufferedWriter(fw);
                    PrintWriter out = new PrintWriter(bww)){
                    
                    
                    System.out.println("Ingrese el id de la cita: ");
                    int idcita = leer.nextInt();
                    System.out.print("Ingresa el motivo de tu cita\n");
                    String motivoCita = leer.next();
                    loadPaciente(aPac);                    
                    
                    System.out.print("Ingresa el id del paciente\n");
                    String idPaciente = leer.next();

                    for(int i=0; i <aPac.size(); i++)
                    {
                        if(aPac.get(i).getId().equals(idPaciente))
                        {
                            //Paciente pacientePC = new Paciente(aPac.get(i).getId(),aPac.get(i).getNombrePaciente());
                            String auxID = aPac.get(i).getId();
                            String auxnomP = aPac.get(i).getNombrePaciente();
                            
                            System.out.println(auxID);
                        }
                        
                    }
                    
                    Paciente pacientePC = new Paciente(auxID,auxnomP);
                    System.out.print("Ingresa el id del doctor\n");
                    String idDoctor = leer.next();
                    
                    System.out.print("Ingresa la fecha de tu cita\n");
                    String fechaCitaString = leer.next();
                    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    Date fechaCita = formato.parse(fechaCitaString);


                    Cita citaInfo = new Cita(idcita, fechaCita, motivoCita);

                    citaInfo.asignarP(pacientePC);
                    
                    
                    out.print(citaInfo.id);           
                    out.print(",");
                    out.println(citaInfo.fechaCita.toString());
                    out.print(",");
                    out.print(citaInfo.motivoCita);
                    out.print(",");
                    out.print(citaInfo.idCita);
                    out.print(",");
                    out.print(citaInfo.idPaciente);
                    out.println(",");


                    }
                 catch(ParseException e) {
                    System.out.println("ParseException catched while writing: " + e.getMessage());
                    } finally {
                        try {
                            if (bw != null) {
                                bw.close();
                            }
                        } catch (IOException e) {
                            System.out.println("IOException catched while closing: " + e.getMessage());
                        }
                    }*/
                    

    }
    
    public static int archivV(String archivo)
    {
        int i = 0;
        
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(archivo));     
            if (br.readLine() == null) 
            {
                System.out.println("archivo vacio");
            }
            
            else i = 1;
        }
        catch(IOException e) {
            System.out.println("IOException catched while writing: " + e.getMessage());
        }
        return i;
    }
    
    
    
    public static void creaAdministrador(HashMap <String,String> mapa, String id, String pw, BufferedWriter bw)throws IOException{
        
    
        if(mapa.containsKey(id))
            {
                System.out.println("\nError\nNo se puede registrar de nuevo el mismo Administrador\n"); 
            }

        else{   
            
            mapa.put(id, pw);  
                System.out.println("\nAdministrador registrado con exito");
            }   
        
            Iterator<String> iterator = mapa.keySet().iterator();
                String inputFilename = "C:\\Users\\aldop\\Documents\\NetBeansProjects\\EvidenciaJava\\EvidenciaJAVA\\src\\main\\java\\evidencia\\consultorio\\Administradores.csv"; 

                BufferedWriter bufferedWriter = null;

                try {
                    bufferedWriter = new BufferedWriter(new FileWriter(inputFilename));

                   while(iterator.hasNext())                                
                    {
                    String llave = iterator.next();                      


                    bufferedWriter.write(llave+","+mapa.get(llave)+"\n");   
                    }

                    }
                 catch(IOException e) {
                    System.out.println("IOException catched while writing: " + e.getMessage());
                    } finally {
                        try {
                            if (bufferedWriter != null) {
                                bufferedWriter.close();
                                System.out.println("\nCambios guardados");
                            }
                        } catch (IOException e) {
                            System.out.println("IOException catched while closing: " + e.getMessage());
                        }
                    }
        
    }
   
    

   public static boolean contrasena(HashMap <String,String> mapa, String id, String pw)
    {
       
       boolean i ;

            if(mapa.containsKey(id))
            {
                if(mapa.get(id).equals(pw))
                i = true;
                
                else
                {
                    System.out.println("Contraseña incorrecta!\n");
                
                    i = false;
                }
            }
       
            else
            {
                System.out.println("Administrador existente\n");
                i = false;
            }
    
        return i;
    }
   

   public static void load(HashMap<String, String> m)
    {
        String inputFilename = "C:\\Users\\aldop\\Documents\\NetBeansProjects\\EvidenciaJava\\EvidenciaJAVA\\src\\main\\java\\evidencia\\consultorio\\Administradores.csv";
        String a [];                
        
        BufferedReader bufferedReader = null;

        try {
            bufferedReader = new BufferedReader(new FileReader(inputFilename));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                a = line.split(",");        
                m.put(a[0],a[1]);           
            }
        } catch(IOException e) {
            System.out.println("IOException catched while reading: " + e.getMessage());
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                System.out.println("IOException catched while closing: " + e.getMessage());
            }
        }

    }
   

   public static void loadPaciente(ArrayList Pac)
   {
        String inputFilename = outputPacientes;
        String a [];                
        
        BufferedReader bufferedReader = null;

        try {
            bufferedReader = new BufferedReader(new FileReader(inputFilename));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                a = line.split(",");        
                Pac.add(a[0]);           
                Pac.add(a[1]);
            }
        } catch(IOException e) {
            System.out.println("IOException catched while reading: " + e.getMessage());
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                System.out.println("IOException catched while closing: " + e.getMessage());
            }
        }      
   }
   
   public static void loadDoctor(ArrayList Doc)
   {
        String inputFilename = outputDoctores;
        String a [];                
        
        BufferedReader bufferedReader = null;

        try {
            bufferedReader = new BufferedReader(new FileReader(inputFilename));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                a = line.split(",");        
                Doc.add(a[0]);          
                Doc.add(a[1]);
                Doc.add(a[2]);
            }
        } catch(IOException e) {
            System.out.println("IOException catched while reading: " + e.getMessage());
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                System.out.println("IOException catched while closing: " + e.getMessage());
            }
        }      
   }
   
}
