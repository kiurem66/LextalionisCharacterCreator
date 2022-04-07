import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;


import org.apache.poi.ss.usermodel.CellCopyPolicy;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class XLS {

    private XLS(){}

    public static void export(Character character, String path) throws IOException{
        XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream("media/template.xlsx"));
        XSSFSheet sheet = wb.getSheet("Scheda");
        writeToCell(sheet, 0, 5, character.getName());
        writeToCell(sheet, 1, 0, character.getBlood());
        writeToCell(sheet, 1, 1, character.getWill());
        writeToCell(sheet, 1, 3, character.getRemainingPx());
        String gen = "";
        if(character.isVampire())
            gen = ((Vampire)character).getGen() + "°";
        else if(!character.getClan().equals("Ghoul")) 
            gen = "Revenant";
        else
            gen = "Ghoul";
        
        writeToCell(sheet, 1, 4, gen);
        writeToCell(sheet, 1, 5, character.getClan());
        writeToCell(sheet, 37, 0, character.getSentiero());
        writeToCell(sheet, 38, 0, character.getFazione());

        writeStyles(sheet, character.sIterator());
        writeInfluencies(sheet, character.inflIterator());
        writeProCon(sheet, character.pIterator());
        writeDisciplines(sheet, character.discIterator());

        //ArrayList<Integer> DisciplineStartIndexees = new ArrayList<Integer>();
        FileOutputStream fileOut = new FileOutputStream(path); 
        wb.write(fileOut);
        fileOut.close();
        wb.close();
    }

    private static void writeToCell(XSSFSheet sheet, int r, int c, String v){
        sheet.getRow(r).getCell(c).setCellValue(v);
    }

    private static void writeToCell(XSSFSheet sheet, int r, int c, int v){
        writeToCell(sheet, r, c, Integer.toString(v));
    }

    private static void writeStyles(XSSFSheet sheet, Iterator<Style> it){
        int i=0;
        int styleIndex = 28;
        while(it.hasNext() && i<2){
            Style s = it.next();
            styleIndex = 28 + i*4;
            writeToCell(sheet, styleIndex, 0, s.getName());
            Iterator<String> its = s.iterator();
            int j=1;
            while(its.hasNext()){
                writeToCell(sheet, styleIndex+j, 0, its.next());
                String lv = new String(new char[j]).replace("\0", "●") + new String(new char[3-j]).replace("\0", "○"); 
                writeToCell(sheet, styleIndex+j, 1, lv);
                j++;
            }
            i++;
        }
    }

    private static void writeInfluencies(XSSFSheet sheet, Iterator<Influenza> it){
        int i = 0;
        final int BASE = 28;
        while(it.hasNext() && i<6){
            Influenza inf = it.next();
            if(inf.getLevel() != 0){
                writeToCell(sheet, BASE+i, 5, inf.getName());
                writeToCell(sheet, BASE+i, 6, inf.getLevel());
                i++;
            }
        }
    }

    private static void writeDisciplines(XSSFSheet sheet, Iterator<Disciplina> it){
        final int BASE = 3;
        int numDisc = 0;
        int i=0;
        while(it.hasNext() && numDisc < 4){
            Disciplina d = it.next();
            if(d.getLevel() > 0){
                writeToCell(sheet, BASE+i, 0, d.getName());
                int j=1;
                for(Disciplina.Power p : d){
                    if(j > 5){
                        sheet.shiftRows(BASE+i+j, 40, 1);
                        XSSFRow r = sheet.createRow(BASE+i+j);
                        CellCopyPolicy cellCopyPolicy = new CellCopyPolicy();
                        r.copyRowFrom(sheet.getRow(6), cellCopyPolicy);
                    }
                    writeToCell(sheet, BASE+i+j, 0, p.nome());
                    j++;
                }
                if(j > 5){
                    i += j;
                }else{
                    i+=6;
                }
                numDisc++;
            }
            
        }
        
    }

    private static void writeProCon(XSSFSheet sheet, Iterator<ProCon> it){
        int iPro = 0;
        int iCon = 0;
        final int BASE = 34;
        while(it.hasNext()){
            ProCon p = it.next();
            if(p.costo() > 0 && iPro < 6){
                writeToCell(sheet, BASE+iPro, 5, p.nome());
                iPro++;
            }else if(iCon < 6){
                writeToCell(sheet, BASE+iCon, 6, p.nome());
                iCon++;
            }
        }
    }

    public static void main(String[] args) throws IOException{
        Character c = new Clan.Ventrue();
        c.setPx(100);
        c.setName("Giorgio");
        c.setFazione("Stronzi");
        c.setSentiero("Faccio il cazzo che voglio");
        Style s = new Style.Acrobatico();
        s.setLevel(3);
        c.addStile(s);
        s = new Style.Cinematografico();
        s.setLevel(2);
        c.addStile(s);

        c.searchInfl("Esercito").setLevel(1);
        c.addInfluenza(new Influenza("Pollo"));
        c.searchInfl("Pollo").setLevel(4);
        c.addProCon(new ProCon("Pirla", -3));
        c.addProCon(new ProCon("Test", 3));
        c.addProCon(new ProCon("Ti incula", 3));

        c.searchDisc("Ascendente").setLevel(5);
        c.searchDisc("Dominazione").setLevel(5);
        c.addDisciplina(new Disciplina.Necromanzia());
        c.searchDisc("Necromanzia").setLevel(5);
        c.addDisciplina(new Disciplina.Demenza());
        c.searchDisc("Demenza").setLevel(5);

        XLS.export(c, "media/new.xlsx");
    }

    
}
