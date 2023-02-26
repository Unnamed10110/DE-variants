package de.variants;

import de.objects.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.*;
import java.util.Random;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.KShortestPaths;
import org.jgrapht.graph.*;

import java.lang.*;

/**
 *
 * @author USER- ACER
 */
public class DE_trigonometric {

    public static void main(String args[]) throws IOException {
        int kvariable = 1;
        long limite = 60 * 30;
        //paso 1: definir M, CR, NP y #generaciones
        float M = (float) 0.2;
        float CR = (float) 0.5;
        int NP = 20;
        int cantGeneraciones = 500;

        for (int gn = 300; gn <= 300; gn = gn + 50) {
            String soli = "solicitudes-nsf-2-" + gn + ".txt";
            int corridan = 1;
            for (int icc = 0; icc < corridan; icc++) {
                String comment = "(" + gn + ")";
                int kpath = 5;
                BufferedWriter popFinal = new BufferedWriter(new FileWriter("E:\\iin\\eon-tesis\\rcsa\\DE-variants\\src\\de\\files\\nsf\\DE_trigonometric\\popFinal-corrida-" + icc + "-" + comment + ".txt"));
                int kp2 = 5;
                for (int kp = 1; kp <= kpath; kp++) {

                    //####################################
                    //lectura de archivo de demandas
                    FileReader frSolicitudes = new FileReader("E:\\iin\\eon-tesis\\rcsa\\DE-variants\\src\\de\\files\\nsf\\solicitudes\\" + soli);
                    BufferedReader brSolicitudes = new BufferedReader(frSolicitudes);
                    List<demand> demandasLista = new ArrayList<>();
                    List<demand> demandasLista2 = new ArrayList<>();
                    String line;
                    List<String[]> par_demanda = new ArrayList<>();
                    int cc = 0;
                    while ((line = brSolicitudes.readLine()) != null) {
                        par_demanda.add(cc, line.split("	"));
                        demand edemanda = new demand((float) cc, Integer.parseInt(par_demanda.get(cc)[0]), Integer.parseInt(par_demanda.get(cc)[1]), Integer.parseInt(par_demanda.get(cc)[2]));
                        demandasLista.add(edemanda);
                        demandasLista2.add(edemanda);
                        cc++;
                    }

                    //####################################
                    List<Link> EnlaceRanuras = new ArrayList<>();
                    //carga de grafo

                    //#################################### vista de enlaces
                    /*for (int i = 0; i < EnlaceRanuras.size(); i++) {
            System.out.println(EnlaceRanuras.get(i).getInicio()+"-"+EnlaceRanuras.get(i).getFin()+":"+EnlaceRanuras.get(i).getLongitud());
            //System.out.println(EnlaceRanuras.get(i).getRanuras().get(i));
        }*/
                    //####################################
                    //asignar ranuras
                    //lista de enlaces
                    //List<Link> EnlaceRanuras = new ArrayList<>();
                    //EnlaceRanuras= asignarFS(1, EnlaceRanuras, demandasLista, directedGraph);
                    //int maxRanura=SU(EnlaceRanuras);
                    //System.out.println("maxRanura:: "+ maxRanura);
                    //***********************************************
                    //***********************************************
                    //***********************************************
                    //***********************************************
                    //***********************************************
                    //***********************************************
                    //***********************************************
                    //***********************************************
                    //DE basico
                    float Gamma = (float) 0.5;
                    //paso 2: crear poblacion inicial
                    List<List<demand>> poblacion = new ArrayList<>();
                    poblacion = generarPoblacion(demandasLista, NP);
                    List<Ofitness> PopFit = new ArrayList<Ofitness>();

                    //posicion relativa de indice (conversion)
                    poblacion = methodRPI(poblacion);
                    //guarda la poblacion inicial para luego comparar la mejora
                    /*List<List<demand>> poblacionInicial=new ArrayList<>();
        poblacionInicial=poblacion;
        float fitnessObjetivoPromedio=0;
        int suInicialPromedio=0;
        float aplInicialPromedio=0;
        for (int i = 0; i < poblacionInicial.size(); i++) {
            List<Link> EnlaceRanurasInicial = new ArrayList<>();
            SimpleDirectedGraph<String, DefaultWeightedEdge> directedGraphInicial = new SimpleDirectedGraph(DefaultWeightedEdge.class);
            int cantEnlacesInicial = cargarGrafo(directedGraphInicial, "E:\\iin\\eon-tesis\\rcsa\\DE-variants\\src\\de\\files\\nsf\\",EnlaceRanurasInicial);
            EnlaceRanurasInicial= asignarFS(kpath, EnlaceRanurasInicial, poblacion.get(i), directedGraphInicial);
                
            fitnessObjetivoPromedio=fitnessObjetivoPromedio+fitness(EnlaceRanurasInicial, poblacion.get(i), directedGraphInicial, kpath);
            suInicialPromedio=suInicialPromedio+SU(EnlaceRanurasInicial);
            aplInicialPromedio=aplInicialPromedio+APL(poblacion.get(i), directedGraphInicial, kpath);

        }
        fitnessObjetivoPromedio=fitnessObjetivoPromedio/poblacionInicial.size();
        suInicialPromedio=suInicialPromedio/poblacionInicial.size();
        aplInicialPromedio=aplInicialPromedio/poblacionInicial.size();
        
        BufferedWriter popInicial = new BufferedWriter(new FileWriter("E:\\iin\\eon-tesis\\rcsa\\DE-variants\\src\\de\\files\\nsf\\DE_trigonometric\\popInicial.txt"));
        popInicial.write("Fitness promedio de la poblacion inicial: "+fitnessObjetivoPromedio+"\n");
        popInicial.write("SU promedio de la poblacion inicial: "+suInicialPromedio+"\n");
        //popInicial.write("APL promedio de la poblacion inicial: "+aplInicialPromedio+"\n");
        popInicial.close();
        //System.out.println("fin escritura");
        //System.in.read();
        
                     */
                    //*****************************************************

                    List<List<demand>> poblacionFinal = new ArrayList<>();

                    //paso 3: generaciones
                    int gene = 0;
                    boolean cpTime = true;
                    boolean cpConvergencia = true;
                    long startTime = new Date().getTime();

                    //for (int i = 0; i < cantGeneraciones; i++)
                    while (cpTime & cpConvergencia) {
                        gene++;
                        System.out.println("Gen: " + gene);
                        for (int j = 0; j < poblacion.size(); j++) {
                            //paso 4: seleccionar 3 individuos
                            int cont = 1;
                            int x = getRandomNumberInRange(0, NP - 1);
                            List<Integer> xlist = new ArrayList<>();
                            xlist.add(x);
                            while (cont != 3) {
                                x = getRandomNumberInRange(0, NP - 1);
                                if (xlist.contains(x) == false) {
                                    xlist.add(x);
                                    cont++;
                                }
                            }
                            List<demand> x1 = new ArrayList<>();
                            List<demand> x2 = new ArrayList<>();
                            List<demand> x3 = new ArrayList<>();
                            x1 = poblacion.get(xlist.get(0));
                            x2 = poblacion.get(xlist.get(1));
                            x3 = poblacion.get(xlist.get(2));

                            //mutacion trigonometrica, p-coeficientes
                            //f1
                            SimpleDirectedGraph<String, DefaultWeightedEdge> directedGraphP1 = new SimpleDirectedGraph(DefaultWeightedEdge.class);
                            List<Link> EnlaceRanurasF1 = new ArrayList<>();
                            int cantEnlacesF1 = cargarGrafo(directedGraphP1, "E:\\iin\\eon-tesis\\rcsa\\DE-variants\\src\\de\\files\\nsf\\", EnlaceRanurasF1);
                            EnlaceRanurasF1 = asignarFS(kp, EnlaceRanurasF1, x1, directedGraphP1);

                            float ff1 = fitness(EnlaceRanurasF1, x1, directedGraphP1);
                            //f2
                            SimpleDirectedGraph<String, DefaultWeightedEdge> directedGraphP2 = new SimpleDirectedGraph(DefaultWeightedEdge.class);
                            List<Link> EnlaceRanurasF2 = new ArrayList<>();
                            int cantEnlacesF2 = cargarGrafo(directedGraphP2, "E:\\iin\\eon-tesis\\rcsa\\DE-variants\\src\\de\\files\\nsf\\", EnlaceRanurasF2);
                            EnlaceRanurasF2 = asignarFS(kp, EnlaceRanurasF2, x2, directedGraphP2);

                            float ff2 = fitness(EnlaceRanurasF2, x2, directedGraphP2);
                            //f3
                            SimpleDirectedGraph<String, DefaultWeightedEdge> directedGraphP3 = new SimpleDirectedGraph(DefaultWeightedEdge.class);
                            List<Link> EnlaceRanurasF3 = new ArrayList<>();
                            int cantEnlacesF3 = cargarGrafo(directedGraphP3, "E:\\iin\\eon-tesis\\rcsa\\DE-variants\\src\\de\\files\\nsf\\", EnlaceRanurasF3);
                            EnlaceRanurasF3 = asignarFS(kp, EnlaceRanurasF3, x3, directedGraphP3);

                            float ff3 = fitness(EnlaceRanurasF3, x3, directedGraphP3);
                            //p prima
                            float pPrima = ff1 + ff2 + ff3;
                            //p1, p2 y p3
                            float p1 = ff1 / pPrima;
                            float p2 = ff2 / pPrima;
                            float p3 = ff3 / pPrima;

                            //paso 5: aplicar mutacion trigonometrica
                            float mutant[] = new float[x1.size()];
                            Random rand0 = new Random();
                            float gammarnd = rand0.nextFloat();
                            if (gammarnd <= Gamma) {

                                for (int k = 0; k < x1.size(); k++) {
                                    mutant[k] = ((x1.get(k).getID() + x2.get(k).getID() + x3.get(k).getID()) / 3) + ((p2 - p1) * (x1.get(k).getID() - x2.get(k).getID())) + (p3 - p2) * (x2.get(k).getID() - x3.get(k).getID()) + (p1 - p3) * (x3.get(k).getID() - x1.get(k).getID());
                                }

                            } else {
                                for (int k = 0; k < x1.size(); k++) {
                                    mutant[k] = M * (x2.get(k).getID() - x3.get(k).getID());
                                }
                                for (int k = 0; k < x1.size(); k++) {
                                    mutant[k] = x1.get(k).getID() + mutant[k];
                                }
                            }

                            //float mutant[]=new float[x1.size()];
                            //paso 6: recombinacion
                            float trial[] = new float[x1.size()];
                            for (int k = 0; k < mutant.length; k++) {
                                Random rand = new Random();
                                float rnd = rand.nextFloat();
                                if (rnd < CR) {
                                    trial[k] = mutant[k];
                                } else {
                                    trial[k] = poblacion.get(j).get(k).getID();
                                }
                            }

                            //intermedio: retornar indices a enteros
                            float trialFinal[] = new float[x1.size()];

                            int posicion = 0;

                            for (int k = 0; k < trial.length; k++) {
                                float menor = 1000;
                                for (int l = 0; l < trial.length; l++) {
                                    if (trial[l] < menor) {
                                        menor = trial[l];
                                        posicion = l;
                                        //System.out.println("posicion: "+ posicion);

                                    }

                                }
                                //System.out.println("posicion:::::::: "+ posicion);
                                trial[posicion] = 1000;
                                trialFinal[posicion] = k;
                                //System.in.read();
                            }

                            /*for (int k = 0; k < trialFinal.length; k++) {
                    System.out.println(k+": trial: "+trialFinal[k]);
                }
                System.in.read();*/
                            //paso 7: aplicar operador de seleccion
                            List<demand> trialDemanda = new ArrayList<>();
                            trialDemanda = demandasLista2;
                            for (int k = 0; k < trialDemanda.size(); k++) {
                                trialDemanda.get(k).setID(trialFinal[k]);
                            }

                            trialDemanda = sortTrial(trialDemanda);
//                System.out.println("::Sorted Trialdemanda ID's::");
//                for (int i = 0; i < trialDemanda.size(); i++) {
//                    System.out.print("::  "+trialDemanda.get(i).getID());
//                }
//                System.in.read();
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++                
                            //k variable
                            kvariable = getRandomNumberInRange(1, kp);
//            System.out.println("kvariable: "+kvariable);
//            System.in.read();

//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++                
                            //fitness del vector objetivo                
                            List<Link> EnlaceRanurasObjetivo = new ArrayList<>();
                            SimpleDirectedGraph<String, DefaultWeightedEdge> directedGraph = new SimpleDirectedGraph(DefaultWeightedEdge.class);
                            int cantEnlaces = cargarGrafo(directedGraph, "E:\\iin\\eon-tesis\\rcsa\\DE-variants\\src\\de\\files\\nsf\\", EnlaceRanurasObjetivo);

                            //ver enlaces
//                System.out.println(":: enlaces antes de asignar\n");
//                for (int i = 0; i < EnlaceRanurasObjetivo.size(); i++) {
//                    System.out.println("#"+i+"----Origen: "+EnlaceRanurasObjetivo.get(i).getInicio()+"---- Destino: "+EnlaceRanurasObjetivo.get(i).getFin());
//                    for (int k = 0; k < EnlaceRanurasObjetivo.get(i).getRanuras().size(); k++) {
//                        System.out.print("["+EnlaceRanurasObjetivo.get(i).getRanuras().get(k) +"]");
//                    }
//                    System.out.print("\n");
//                }
//                System.in.read();
                            EnlaceRanurasObjetivo = asignarFS(kvariable, EnlaceRanurasObjetivo, poblacion.get(j), directedGraph);

                            //ver enlaces
//                System.out.println(":: enlaces despues de asignar\n");
//                for (int i = 0; i < EnlaceRanurasObjetivo.size(); i++) {
//                    System.out.println("#"+i+"----Origen: "+EnlaceRanurasObjetivo.get(i).getInicio()+"---- Destino: "+EnlaceRanurasObjetivo.get(i).getFin());
//                    for (int k = 0; k < EnlaceRanurasObjetivo.get(i).getRanuras().size(); k++) {
//                        System.out.print("["+EnlaceRanurasObjetivo.get(i).getRanuras().get(k) +"]");
//                    }
//                    System.out.print("\n");
//                }
//                System.in.read();
                            /*
                System.out.println("pobObjetivo-demand:");
                for (int i = 0; i < poblacion.get(j).size(); i++) {
                    System.out.print(":"+poblacion.get(j).get(i).getFS());
                }
                System.in.read();
                             */
                            float fitnessObjetivo = fitness(EnlaceRanurasObjetivo, poblacion.get(j), directedGraph);

//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++                
                            //fitness del vector trial
                            List<Link> EnlaceRanurasTrial = new ArrayList<>();
                            SimpleDirectedGraph<String, DefaultWeightedEdge> directedGraph2 = new SimpleDirectedGraph(DefaultWeightedEdge.class);
                            int cantEnlaces2 = cargarGrafo(directedGraph2, "E:\\iin\\eon-tesis\\rcsa\\DE-variants\\src\\de\\files\\nsf\\", EnlaceRanurasTrial);
                            EnlaceRanurasTrial = asignarFS(kvariable, EnlaceRanurasTrial, trialDemanda, directedGraph2);

                            /*
                System.out.println("pobTrial:");
                for (int i = 0; i < trialDemanda.size(); i++) {
                    System.out.print(":"+trialDemanda.get(i).getFS());
                }
                System.in.read();
                             */
                            float fitnessTrial = fitness(EnlaceRanurasTrial, trialDemanda, directedGraph2);
                            //System.out.println("fittrial: "+ fitnessTrial);
                            //System.in.read();
                            /*for (int k = 0; k < poblacion.get(j).size(); k++) {
                    System.out.println("origen-destino-pob: "+poblacion.get(j).get(k).getOrigen()+"-"+poblacion.get(j).get(k).getDestino() );
                    System.out.println("origen-destino-trial: "+trialDemanda.get(k).getOrigen()+"-"+trialDemanda.get(k).getDestino() );
                    System.in.read();
                }*/

 /*
                System.out.println("fitness objetivo: "+fitnessObjetivo);
                System.out.println("fitness trial: "+fitnessTrial);
                System.in.read();
                             */
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++                
                            if (fitnessTrial <= fitnessObjetivo) {
                                for (int jj = 0; jj < trialDemanda.size(); jj++) {
                                    float id = trialDemanda.get(jj).getID();
                                    trialDemanda.get(jj).setID(id / poblacion.get(0).size());

                                    //System.out.println("ID: "+poblacion.get(i).get(jj).getID());
                                    //System.in.read();
                                }
                                poblacion.set(j, trialDemanda);
                                PopFit.add(j, new Ofitness(fitnessTrial, SU(EnlaceRanurasTrial)));

                            } else {
                                PopFit.add(j, new Ofitness(fitnessObjetivo, SU(EnlaceRanurasObjetivo)));
                            }

                        }//fin del for de poblacion

                        if (gene >= cantGeneraciones) {
                            cpConvergencia = false;
                        }
                        long endTime = new Date().getTime();
                        long duration = (endTime - startTime) / 1000;
                        //System.out.println("drtn: "+duration);s

                        if (duration > limite) {
                            cpTime = false;
                        }

                    }//fin del for de generaciones
                    poblacionFinal = poblacion;
                    float fitnessObjetivoMejorFinal = 0;
                    int suInicialMejorFinal = 0;
                    //float aplInicialMejorFinal=0;
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                    Ofitness mejor = new Ofitness(100, 0);
                    for (int i = 0; i < PopFit.size(); i++) {
                        if (PopFit.get(i).GetFit() < mejor.GetFit()) {
                            mejor = PopFit.get(i);
                        }
                    }
//                    System.out.println("Mejor:::::::::");
//                    System.out.println("Fitness mejor:: " + mejor.GetFit());
//                    System.out.println("SU mejor:: " + mejor.GetSu());

//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//                    int suAux = 0;
//                    for (int i = 0; i < poblacionFinal.size(); i++) {
//                        List<Link> EnlaceRanurasFinal = new ArrayList<>();
//                        SimpleDirectedGraph<String, DefaultWeightedEdge> directedGraphFinal = new SimpleDirectedGraph(DefaultWeightedEdge.class);
//                        int cantEnlacesFinal = cargarGrafo(directedGraphFinal, "E:\\iin\\eon-tesis\\rcsa\\DE-variants\\src\\de\\files\\nsf\\", EnlaceRanurasFinal);
//                        EnlaceRanurasFinal = asignarFS(kvariable, EnlaceRanurasFinal, poblacion.get(i), directedGraphFinal);
//
//                        if (i == 1) {
//                            fitnessObjetivoMejorFinal = fitness(EnlaceRanurasFinal, poblacion.get(i), directedGraphFinal);
//                            suInicialMejorFinal = SU(EnlaceRanurasFinal);
//                            //aplInicialMejorFinal=b2(poblacion.get(i), directedGraphFinal, kp);
//                        } else {
//                            suAux = SU(EnlaceRanurasFinal);
//                            if (suAux < suInicialMejorFinal) {
//                                suInicialMejorFinal = suAux;
//                                fitnessObjetivoMejorFinal = fitness(EnlaceRanurasFinal, poblacion.get(i), directedGraphFinal);
//                                //aplInicialMejorFinal=b2(poblacion.get(i), directedGraphFinal, kp2);
//                            }
//                        }
//                    }

                    popFinal.write("- K: " + kp + "\n");
                    popFinal.write("Best Fitness de la poblacion final: " + mejor.GetFit() + "\n");
                    popFinal.write("Best SU de la poblacion final: " + mejor.GetSu() + "\n");
                    System.out.println("Fitness promedio de la poblacion final: " + mejor.GetFit());
                    System.out.println("SU promedio de la poblacion final: " + mejor.GetSu());
                    popFinal.write("::::::::::::::::::::::::::::::::::\n");
                    System.out.println("fin escritura de ciclo");
                    kp2--;
                }
                popFinal.close();
            }

        }
    }

    private static int cargarGrafo(SimpleDirectedGraph<String, DefaultWeightedEdge> directedGraph, String path, List<Link> EnlaceRanuras) {

        int cantEnlaces = 0;
        try {
            FileReader fr = new FileReader(path + "\\edges.txt");
            String[] edgeText;
            DefaultWeightedEdge ed;

            try (BufferedReader entrada = new BufferedReader(fr)) {
                String line;
                int cc = 0;
                while ((line = entrada.readLine()) != null) {
                    cantEnlaces++;
                    edgeText = line.split("\t");
                    //System.out.println("split:"+ edgeText[0]);
                    //System.out.println("split:"+ edgeText[1]);
                    //System.out.println("split:"+ edgeText[2]);                    
                    //System.in.read();
                    directedGraph.addVertex(edgeText[0]);
                    directedGraph.addVertex(edgeText[1]);
                    ed = directedGraph.addEdge(edgeText[0], edgeText[1]);
                    //System.out.println(edgeText[0]+"-"+ edgeText[1]+"-"+ edgeText[2]);
                    directedGraph.setEdgeWeight(ed, Double.parseDouble(edgeText[2]));

                    //********************
                    //********************
                    List<Integer> ranurasVacias = new ArrayList<>();
                    for (int ix = 0; ix < 10000; ix++) {
                        ranurasVacias.add(ix, -1);

                    }
                    Link enlacel = new Link(Integer.parseInt(edgeText[0]), Integer.parseInt(edgeText[1]), Integer.parseInt(edgeText[2]), ranurasVacias);
                    EnlaceRanuras.add(enlacel);

                    //bidereccion
                    //ed = directedGraph.addEdge(edgeText[1], edgeText[0]);
                    //directedGraph.setEdgeWeight(ed, Double.parseDouble(edgeText[2]));
                    //System.out.println("edge::"+ed);
                    //System.in.read();
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Archivo no encontrado: " + ex);
        } catch (IOException ex) {
        }
        return cantEnlaces;
    }

    private static List<GraphPath<String, DefaultWeightedEdge>> kcaminos(SimpleDirectedGraph<String, DefaultWeightedEdge> directedGraph, String origen, String destino, int k) {

        //obtener k rutas
        KShortestPaths caminosCandidatos = new KShortestPaths(directedGraph, origen, k);
        List<GraphPath<String, DefaultWeightedEdge>> paths = caminosCandidatos.getPaths(destino);

        //listar caminos(nodos) y costos(km)
        /*if (paths!=null) {
            int cc=0;
            while (cc<paths.size()) {                
                
                System.out.println("path #"+cc+"->"+paths.get(cc));
                System.out.println("costo->"+ paths.get(cc).getWeight());
                cc++;
            }
        }*/
        return paths;
        //System.out.println("path 0:" + caminosCandidatos.getPaths(destino));

    }

    private static List<Link> asignarFS(int k, List<Link> enlaceRanuras, List<demand> demandasLista, SimpleDirectedGraph<String, DefaultWeightedEdge> directedGraph) throws IOException {

        List<Link> enlaceRanuras2 = new ArrayList<Link>(enlaceRanuras);
        for (int i = 0; i < demandasLista.size(); i++) {
            int origen = demandasLista.get(i).getOrigen();
            int destino = demandasLista.get(i).getDestino();
            int FS = demandasLista.get(i).getFS();

            List<GraphPath<String, DefaultWeightedEdge>> paths = kcaminos(directedGraph, Integer.toString(origen), Integer.toString(destino), k);

            if (paths.get(k - 1).getWeight() <= 320) {
                FS = 12;
            }
            if (paths.get(k - 1).getWeight() > 320 & paths.get(k - 1).getWeight() <= 400) {
                FS = 13;
            }
            if (paths.get(k - 1).getWeight() > 400 & paths.get(k - 1).getWeight() <= 640) {
                FS = 14;
            }
            if (paths.get(k - 1).getWeight() > 640 & paths.get(k - 1).getWeight() <= 720) {
                FS = 15;
            }
            if (paths.get(k - 1).getWeight() > 720) {
                FS = 16;
            }

            //FS=getRandomNumberInRange(12, 16);
            //System.out.println("Gral::"+paths.get(k-1));
            List<Integer> rutaSimplificada = parserRuta(paths, k);

//            for (int j = 0; j < rutaSimplificada.size(); j++) {
//                System.out.print(":::" + rutaSimplificada.get(j));
//            }
//            
//            System.in.read();
            List<Link> pathRanuras = new ArrayList<>();
            for (int j = 0; j < rutaSimplificada.size() - 1; j++) {
                for (int l = 0; l < enlaceRanuras.size(); l++) {
                    if (rutaSimplificada.get(j) == enlaceRanuras.get(l).getInicio() & rutaSimplificada.get(j + 1) == enlaceRanuras.get(l).getFin()) {
                        pathRanuras.add(enlaceRanuras.get(l));
                    }

                    if (rutaSimplificada.get(j) == enlaceRanuras.get(l).getFin() & rutaSimplificada.get(j + 1) == enlaceRanuras.get(l).getInicio()) {
                        pathRanuras.add(enlaceRanuras.get(l));
                    }

                }
            }

            //ver enlaces a utilizar
//            System.out.println("--------------- Demanda :: Origen: "+origen+":: Destino: "+destino+":: FS: "+FS+"\n" );
//            System.out.println(":: Enlaces a utilizar antes de asignar:: ");
//            for (int j = 0; j < pathRanuras.size(); j++) {
//                System.out.println("\n#"+j+"----Origen: "+pathRanuras.get(j).getInicio()+"---- Destino: "+pathRanuras.get(j).getFin());
//                for (int l = 0; l < pathRanuras.get(j).getRanuras().size(); l++) {
//                    System.out.print("["+pathRanuras.get(j).getRanuras().get(l)+"]");
//                }
//            }
//            System.in.read();
//---------------------------------- asignacion--------------------------------------------------
            List<Integer> aux = new ArrayList<Integer>();
            boolean espacio = false;
            int inicio = 0;
            while (!espacio) {
                for (int j = inicio; j < pathRanuras.get(0).getRanuras().size(); j++) {
                    if (aux.size() == FS) {
                        break;
                    } else if (pathRanuras.get(0).getRanuras().get(j) != 1) {
                        aux.add(j);
                    } else {
                        aux.clear();
                    }
                }
                boolean checked = false;
                for (int j = 1; j < pathRanuras.size(); j++) {
                    int cc = 0;
                    for (int l = 0; l < aux.size(); l++) {
                        if (pathRanuras.get(j).getRanuras().get(aux.get(l)) != 1) {
                            cc++;
                        }
                    }
                    if (cc == aux.size()) {
                        checked = true;
                    } else {
                        checked = false;
                    }
                }
                if (checked == false) {
                    inicio = aux.get(aux.size() - 1);
                    aux.clear();
                } else {
                    espacio = true;
                }
            }
            //::::::::::::::::::::::::::::::::::::::::::::::2

            for (int j = 0; j < pathRanuras.size(); j++) {
                for (int l = 0; l < aux.size(); l++) {
                    pathRanuras.get(j).getRanuras().set(aux.get(l), 1);
                }
            }

//-----------------------------------------------------------------------------------------------            
            for (int j = 0; j < pathRanuras.size(); j++) {
                for (int l = 0; l < enlaceRanuras.size(); l++) {
                    if (pathRanuras.get(j).getInicio() == enlaceRanuras.get(l).getInicio() & pathRanuras.get(j).getFin() == enlaceRanuras.get(l).getFin()) {
                        enlaceRanuras2.get(l).setRanuras(pathRanuras.get(j).getRanuras());
                        //enlaceRanuras2.add(pathRanuras.get(j));
                    } else if (l == 0) {
                        //enlaceRanuras2.add(enlaceRanuras.get(l));
                    }

                }
            }

        }
        return enlaceRanuras2;
    }

    private static List<Integer> parserRuta(List<GraphPath<String, DefaultWeightedEdge>> paths, int k) {
        List<Integer> rutaSimplificada = new ArrayList<>();
        String parte[] = paths.get(k - 1).toString().split(",");
        for (int i = 0; i < parte.length; i++) {
            String parte2[] = parte[i].split(":");
            for (int j = 0; j < parte2.length; j++) {
                parte2[j] = parte2[j].replaceAll("\\D+", "");
                rutaSimplificada.add(Integer.parseInt(parte2[j]));
            }

        }
        for (int i = 1; i < rutaSimplificada.size(); i++) {
            if (rutaSimplificada.get(i) == rutaSimplificada.get(i - 1)) {
                rutaSimplificada.remove(i);
            }

        }

        return rutaSimplificada;
    }

    private static int SU(List<Link> EnlaceRanuras) {
        int maxRanura = 0;
        for (int i = 0; i < EnlaceRanuras.size(); i++) {
            for (int j = 0; j < EnlaceRanuras.get(i).getRanuras().size(); j++) {
                if (EnlaceRanuras.get(i).getRanuras().get(j) == 1) {
                    if (j > maxRanura) {
                        maxRanura = j;

                    }
                }
            }

        }
        //double mx=maxRanura*0.9;
        //maxRanura=(int)mx;
        return maxRanura;
    }

    private static float APL(List<demand> demandasLista, SimpleDirectedGraph<String, DefaultWeightedEdge> directedGraph, int k) {
        float aplVal = 0;

        for (int i = 0; i < demandasLista.size(); i++) {
            List<GraphPath<String, DefaultWeightedEdge>> paths = kcaminos(directedGraph, Integer.toString(demandasLista.get(i).getOrigen()), Integer.toString(demandasLista.get(i).getDestino()), k);
            aplVal = aplVal + (float) paths.get(0).getWeight();
        }
        aplVal = aplVal / (demandasLista.size());
        return aplVal;
    }

    private static int b1(List<demand> demandasLista) {
        int b1Val = 0;
        for (int i = 0; i < demandasLista.size(); i++) {
            b1Val = b1Val + demandasLista.get(i).getFS();
        }

        return b1Val;
    }

    private static float b2(List<demand> demandasLista, SimpleDirectedGraph<String, DefaultWeightedEdge> directedGraph, int k) {
        float b2Val = 0;
        for (int i = 0; i < demandasLista.size(); i++) {
            List<GraphPath<String, DefaultWeightedEdge>> paths = kcaminos(directedGraph, Integer.toString(demandasLista.get(i).getOrigen()), Integer.toString(demandasLista.get(i).getDestino()), k);
            b2Val = b2Val + (float) paths.get(k - 1).getWeight();
        }
        b2Val = b2Val / (demandasLista.size());

        return b2Val;
    }

    private static List<List<demand>> generarPoblacion(List<demand> demandasLista, int NP) throws IOException {
        List<List<demand>> poblacion = new ArrayList<List<demand>>();
        for (int i = 0; i < NP; i++) {
            Collections.shuffle(demandasLista);
            //List<demand> dmd=new ArrayList<demand>();

            List<demand> copy = new ArrayList<>(demandasLista);
//            for (int j = 0; j < demandasLista.size(); j++) {
//                int o=demandasLista.get(j).getOrigen();
//                int d=demandasLista.get(j).getDestino();
//                int fs=demandasLista.get(j).getFS();
//                float id=demandasLista.get(j).getID();
//                demand dd=new demand(id, o, d, fs);
//                dmd.add(dd);
//            }
            poblacion.add(i, copy);

            //********************** ver lista de de miembros de  la poblacion
//            System.out.println("miembro (en metodo) #"+i+"\n");
//            for (int j = 0; j < poblacion.get(i).size(); j++) {
//                System.out.print("------"+poblacion.get(i).get(j).getID());
//                ;
//            }
//            System.out.println("\n");
            //System.in.read();
        }

//        for (int KKL = 0; KKL < NP; KKL++) {
//            //********************** ver lista de de miembros de  la poblacion
//            System.out.println("miembro (en metodo) #"+KKL+"\n");
//            for (int j = 0; j < poblacion.get(KKL).size(); j++) {
//                System.out.print("++++++"+poblacion.get(KKL).get(j).getID());
//                ;
//            }
//            System.out.println("\n");
//            
//        }
//        System.in.read();
        return poblacion;
    }

    private static int getRandomNumberInRange(int min, int max) {

        Random r = new Random();
        return r.ints(min, (max + 1)).findFirst().getAsInt();
    }

    private static List<demand> sortTrial(List<demand> trialDemanda) throws IOException {
        List<demand> nuevoTrial = new ArrayList<>();

        //+++++++++++++++++++++++++++++++++++++++++++++++
//        for (int i = 0; i < trialDemanda.size(); i++) {
//            for (int j = 0; j < trialDemanda.size(); j++) {
//                if (trialDemanda.get(j).getID()==i) {
//                    nuevoTrial.add(trialDemanda.get(j));
//                }
//            }
//        }
        //+++++++++++++++++++++++++++++++++++++++++++++++
        //nuevoTrial=trialDemanda;
        for (int i = 0; i < trialDemanda.size(); i++) {
            nuevoTrial.add(null);
        }

        for (int i = 0; i < trialDemanda.size(); i++) {
            //nuevoTrial.add((int)trialDemanda.get(i).getID(),trialDemanda.get(i));
            nuevoTrial.set((int) trialDemanda.get(i).getID(), getDemandPos(trialDemanda, i));
            //System.out.println("index: "+(int)trialDemanda.get(i).getID());

        }
        //System.in.read();

//        System.out.println("::Sorted nuevoTrial ID's::");
//        for (int i = 0; i < nuevoTrial.size(); i++) {
//            System.out.print("::  "+nuevoTrial.get(i).getID());
//        }
//        System.in.read();
        return nuevoTrial;

    }

    private static float fitness(List<Link> enlaceRanura, List<demand> demandasLista, SimpleDirectedGraph<String, DefaultWeightedEdge> directedGraph) throws IOException {
        float fitnessValue = 0;
        float suVal = SU(enlaceRanura);
        int b1Val = b1(demandasLista);

        /*System.out.println("val SU: "+suVal);
        System.out.println("val b1: "+b1Val);
        System.in.read();*/
        //float aplVal=APL(demandasLista, directedGraph, k);
        //float b2Val=b2(demandasLista, directedGraph, k);
        /*System.out.println("val APL: "+aplVal);
        System.out.println("val b2: "+b2Val);
        System.in.read();*/
        //fitnessValue=(float) (0.5*(suVal/b1Val));//+0.5*(aplVal/b2Val));
        fitnessValue = (float) (suVal / b1Val);//+0.5*(aplVal/b2Val));

        return fitnessValue;
    }

    private static List<List<demand>> methodRPI(List<List<demand>> poblacion) throws IOException {

        List<List<demand>> poblacion2 = new ArrayList<List<demand>>();
        for (int i = 0; i < poblacion.size(); i++) {

            //***********************************************
//            System.out.println(":id entero::  ");
//            for (int j = 0; j < poblacion.get(i).size(); j++) {
//                System.out.print("::  "+poblacion.get(i).get(j).getID());
//                
//            }
//            System.out.println("\n");
            //***********************************************
            List<demand> Listdmd = new ArrayList<demand>();
            for (int jj = 0; jj < poblacion.get(i).size(); jj++) {
                float id = poblacion.get(i).get(jj).getID();
                float newID = id / poblacion.get(i).size();
                //poblacion.get(i).get(jj).setID(newID);  //error: esto setea todos los elementos

                int o2 = poblacion.get(i).get(jj).getOrigen();
                int d2 = poblacion.get(i).get(jj).getDestino();
                int fs2 = poblacion.get(i).get(jj).getFS();
                float id2 = newID;
                demand dmd = new demand(id2, o2, d2, fs2);
                Listdmd.add(dmd);

            }

            poblacion2.add(Listdmd);

//            List<demand> copy = new ArrayList<>(poblacion.get(i));
//            poblacion2.add(copy);
            //***********************************************
//            System.out.println("\n:id decimal::  ");
//            for (int j = 0; j < poblacion2.get(i).size(); j++) {
//                System.out.print("::  "+poblacion2.get(i).get(j).getID());
//                
//            }
//            System.out.println("\n---------------------------------------------\n");
//            System.in.read();
            //***********************************************
        }

        return poblacion2;
    }

    private static demand getDemandPos(List<demand> trialdemanda, int i) {
        demand newD = null;
        for (int j = 0; j < trialdemanda.size(); j++) {
            if ((int) trialdemanda.get(j).getID() == i) {
                newD = trialdemanda.get(j);
                break;
            }
        }
        return newD;
    }
}
