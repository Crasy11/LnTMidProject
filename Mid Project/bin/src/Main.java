import java.io.InputStreamReader;
import java.io.File;  
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.io.FileReader;
import java.util.StringTokenizer;
import java.io.FileWriter;
import java.io.BufferedWriter;


public class Main {
	
	public static void main(String[] args) throws IOException {
		Scanner masuk = new Scanner(System.in);
		String pilih;
		boolean lanjutkan = true;
		System.out.println("==============================");
		System.out.println("[Pendataan Karyawan PT Musang]");
		System.out.println("==============================");
		
		while(lanjutkan) {	
		System.out.println("\n");
		System.out.println("1. Input data karyawan");
		System.out.println("2. View data karyawan");
		System.out.println("3. Update data karyawan");
		System.out.println("4. Delete data karyawan");
		System.out.println("5. Exit");
		System.out.println("=======================");
		System.out.print("Input Menu: ");
		System.out.print("\n");
		pilih = masuk.next();
		
		
		switch(pilih){
		
		case "1":
			System.out.println("==================");
			System.out.println("Input Data Karyawan");
			System.out.println("==================");
			tambahkanData();
			break;
		
		case "2":
			System.out.println("==================");
			System.out.println("View Data Karyawan");
			System.out.println("==================");
			tampilkanData();
			break;
		case "3":
			System.out.println("==================");
			System.out.println("Update Data Karyawan");
			System.out.println("==================");
//			updateData();
			break;
		case "4":
			System.out.println("==================");
			System.out.println("Delete Data Karyawan");
			System.out.println("==================");
			deleteData();
			break;
		case "5":
			System.exit(0);
			break;
		default :
			System.err.println("Input tidak ditemukkan.\nSilakan pilih [1-5]");
		}	
		}
	
	}
	
	private static void tambahkanData() throws IOException{
		FileWriter fileOutput = new FileWriter("datakaryawan.txt");
		BufferedWriter bufferOutput = new BufferedWriter(fileOutput);
		
		Scanner terminalInput = new Scanner(System.in);
		String kode, nama, jenisKelamin, jabatan;
		Integer gaji;
		
		System.out.print("Input Kode Karyawan:");
		kode = terminalInput.nextLine();
		System.out.print("Input Nama Karyawan:");
		nama = terminalInput.nextLine();
		System.out.print("Input Jenis Kelamin Karyawan:");
		jenisKelamin = terminalInput.nextLine();
		System.out.print("Input Jabatan Karyawan:");
		jabatan = terminalInput.nextLine();
		System.out.print("Input Gaji Karyawan:");
		gaji = terminalInput.nextInt();
		
		
		System.out.print("\n");
		System.out.println("Data berhasil ditambahkan!");
		
		String[] keywords = {kode+","+nama+","+jenisKelamin+","+jabatan+","+gaji};
		System.out.println(Arrays.toString(keywords));
		boolean isExist = cekKode(keywords, false);
		if (!isExist){

//          String punulisTanpaSpasi = penulis.replaceAll("\\s+","");
//          String primaryKey = punulisTanpaSpasi+"_"+tahun+"_"+nomorEntry;
          System.out.println("\nData yang akan anda masukan adalah");
          System.out.println("----------------------------------------");
          System.out.println("Kode  : " + kode);
          System.out.println("Nama : " + nama);
          System.out.println("Jenis Kelamin      : " + jenisKelamin);
          System.out.println("Jabatan        : " + jabatan);
          System.out.println("Gaji     : " + gaji);

          boolean isTambah = getYesorNo("Apakah akan ingin menambah data tersebut? ");

          if(isTambah){
              bufferOutput.write(kode + "," +nama+ ","+ jenisKelamin+"," + jabatan + ","+ gaji);
              bufferOutput.newLine();
              bufferOutput.flush();
          }

      } else {
          System.out.println("data yang anda akan masukan sudah tersedia dengan data berikut:");
          cekKode(keywords,true);
      }


      bufferOutput.close();
  }	
	
	private static boolean cekKode(String[] keywords, boolean isDisplay) throws IOException{
		
		FileReader fileInput = new FileReader("datakaryawan.txt");
		BufferedReader BI = new BufferedReader(fileInput);
		
		String data = BI.readLine();
		boolean isExist = false;
		int nomor = 0;
		
		if(isDisplay)
		System.out.print("---------------------------------------------------------------------------------------------------------------------------------\n");
		System.out.println("\n| No |Kode Karyawan\t|Nama Karyawan\t\t\t|Jenis Kelamin\t\t|Jabatan\t\t|Gaji Karyawan\t\t|");
		System.out.print("---------------------------------------------------------------------------------------------------------------------------------\n");
		
		while(data != null){
			
			isExist = true;
			
			for(String keyword:keywords){
				isExist = isExist && data.toLowerCase().contains(keyword.toLowerCase());
			}
			
			if(isExist){
				if (isDisplay) {
				nomor++;
				StringTokenizer stringToken = new StringTokenizer(data, ",");
				System.out.printf("| %3d|", nomor);
				
				System.out.printf(" %17s|",stringToken.nextToken());
				System.out.printf("%31s|",stringToken.nextToken());
				System.out.printf("%23s|",stringToken.nextToken());
				System.out.printf("%23s|",stringToken.nextToken()); 
				System.out.printf("%23s|",stringToken.nextToken());
				System.out.print("\n");
				
			} else {
				break;
			}
			}
			System.out.println(isExist);
			data = BI.readLine();
		}
		System.out.print("---------------------------------------------------------------------------------------------------------------------------------\n");
	
		return isExist;
	}
	
	private static void updateData() throws IOException{
        File database = new File("datakaryawan.txt");
        FileReader fileInput = new FileReader(database);
        BufferedReader bufferedInput = new BufferedReader(fileInput);

        
        File tempData = new File("tempData.txt");
        FileWriter fileOutput = new FileWriter(tempData);
        BufferedWriter bufferedOutput = new BufferedWriter(fileOutput);

        
        System.out.println("Data Karyawan");
        tampilkanData();

        
        Scanner terminalInput = new Scanner(System.in);
        System.out.print("\nMasukan nomor urut Karyawan yang akan diupdate: ");
        int updateNum = terminalInput.nextInt();


        String data = bufferedInput.readLine();
        int entryCounts = 0;

        while (data != null){
            entryCounts++;

            StringTokenizer st = new StringTokenizer(data,",");

            if (updateNum == entryCounts){
                System.out.println("\nData yang ingin anda update adalah:");
                System.out.println("---------------------------------------");
                System.out.println("Kode           : " + st.nextToken());
                System.out.println("Nama               : " + st.nextToken());
                System.out.println("Jenis Kelamin             : " + st.nextToken());
                System.out.println("Jabatan            : " + st.nextToken());
                System.out.println("Gaji               : " + st.nextToken());

                

                

                String[] fieldData = {"Kode","Nama","Jenis Kelamin","Jabatan"};
                String[] tempDt = new String[5];

                st = new StringTokenizer(data,",");
                String originalData = st.nextToken();

                for(int i=0; i < fieldData.length ; i++) {
                    boolean isUpdate = getYesorNo("apakah anda ingin merubah " + fieldData[i]);
                    originalData = st.nextToken();
                    if (isUpdate){
                            terminalInput = new Scanner(System.in);
                            System.out.print("\nMasukan " + fieldData[i] + " baru: ");
                            tempDt[i] = terminalInput.nextLine();
                        

                    } else {
                        tempDt[i] = originalData;
                    }
                }

                // tampilkan data baru ke layar
                st = new StringTokenizer(data,",");
                st.nextToken();
                System.out.println("\nData baru anda ");
                System.out.println("---------------------------------------");
                System.out.println("Kode               : " + st.nextToken() + " --> " + tempDt[0]);
                System.out.println("Nama             : " + st.nextToken() + " --> " + tempDt[1]);
                System.out.println("Jenis Kelamin            : " + st.nextToken() + " --> " + tempDt[2]);
                System.out.println("Jabatan               : " + st.nextToken() + " --> " + tempDt[3]);


                boolean isUpdate = getYesorNo("apakah anda yakin ingin mengupdate data tersebut");

                if (isUpdate){

                    // cek data baru di database
                    boolean isExist = cekKode(tempDt,false);

                    if(isExist){
                        System.err.println("data buku sudah ada di database, proses update dibatalkan, \nsilahkan delete data yang bersangkutan");
                        // copy data
                        bufferedOutput.write(data);

                    } else {

                        // format data baru kedalam database
                        String kode = tempDt[0];
                        String nama = tempDt[1];
                        String jenisKelamin = tempDt[2];
                        String jabatan = tempDt[3];
                        String gaji = tempDt[4];
                        

                        bufferedOutput.write(kode + "," + nama + ","+ jenisKelamin +"," + jabatan + ","+gaji);
                    }
                } else {
                    bufferedOutput.write(data);
                }
            } else {
                bufferedOutput.write(data);
            }
            bufferedOutput.newLine();

            data = bufferedInput.readLine();
        }

        bufferedOutput.flush();

        database.delete();
        tempData.renameTo(database);

    }
	
	private static void deleteData() throws  IOException{
        
        File database = new File("datakaryawan.txt");
        FileReader fileInput = new FileReader(database);
        BufferedReader bufferedInput = new BufferedReader(fileInput);

        
        File tempData = new File("tempData.txt");
        FileWriter fileOutput = new FileWriter(tempData);
        BufferedWriter bufferedOutput = new BufferedWriter(fileOutput);

        System.out.println("Data karyawan");
        tampilkanData();

        
        Scanner terminalInput = new Scanner(System.in);
        System.out.print("\nMasukan nomor urut karyawan yang akan dihapus: ");
        int deleteNum = terminalInput.nextInt();


        boolean isFound = false;
        int entryCounts = 0;

        String data = bufferedInput.readLine();

        while (data != null){
            entryCounts++;
            boolean isDelete = false;

            StringTokenizer st = new StringTokenizer(data,",");

            
            if (deleteNum == entryCounts){
                System.out.println("\nData yang ingin anda hapus adalah:");
                System.out.println("-----------------------------------");
                System.out.println("Kode       : " + st.nextToken());
                System.out.println("Nama           : " + st.nextToken());
                System.out.println("Jenis Kelamin         : " + st.nextToken());
                System.out.println("Jabatan        : " + st.nextToken());
                System.out.println("Gaji           : " + st.nextToken());

                isDelete = getYesorNo("Apakah anda yakin akan menghapus?");
                isFound = true;
            }

            if(isDelete){
                System.out.println("Data berhasil dihapus");
            } else {
                
                bufferedOutput.write(data);
                bufferedOutput.newLine();
            }
            data = bufferedInput.readLine();
        }

        if(!isFound){
            System.err.println("Data Karyawan tidak ditemukan");
        }

        bufferedOutput.flush();
        database.delete();
        tempData.renameTo(database);

    }
	
	
	private static void tampilkanData() throws IOException{
		FileReader fileInput;
		BufferedReader bufferInput;
		
		try {
			fileInput = new FileReader("datakaryawan.txt");
			bufferInput = new BufferedReader(fileInput);
		} catch(Exception e) {
			System.err.println("Database tidak ditemukan");
			System.err.println("Silakan tambah data terlebih dahulu");
			return;
		}
		String data = bufferInput.readLine();

		
		System.out.print("---------------------------------------------------------------------------------------------------------------------------------\n");
		System.out.println("\n| No |Kode Karyawan\t|Nama Karyawan\t\t\t|Jenis Kelamin\t\t|Jabatan\t\t|Gaji Karyawan\t\t|");
		System.out.print("---------------------------------------------------------------------------------------------------------------------------------\n");
		
		int no = 0;
		while(data != null) {
			no++;
		StringTokenizer stringToken = new StringTokenizer(data,",");
		System.out.printf("| %3d|", no);
		
		System.out.printf(" %17s|",stringToken.nextToken());
		System.out.printf("%31s|",stringToken.nextToken());
		System.out.printf("%23s|",stringToken.nextToken());
		System.out.printf("%23s|",stringToken.nextToken()); 
		System.out.printf("%23s|",stringToken.nextToken());
		System.out.print("\n");
		
		data = bufferInput.readLine();
		}
		System.out.print("---------------------------------------------------------------------------------------------------------------------------------\n");
	}
	
	private static boolean getYesorNo(String message){
        Scanner terminalInput = new Scanner(System.in);
        System.out.print("\n"+message+" (y/n)? ");
        String pilihanUser = terminalInput.next();

        while(!pilihanUser.equalsIgnoreCase("y") && !pilihanUser.equalsIgnoreCase("n")) {
            System.err.println("Pilihan anda bukan y atau n");
            System.out.print("\n"+message+" (y/n)? ");
            pilihanUser = terminalInput.next();
        }

        return pilihanUser.equalsIgnoreCase("y");

    }

	private static void clearScreen(){
        try {
            if (System.getProperty("os.name").contains("Windows")){
                new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033\143");
            }
        } catch (Exception ex){
            System.err.println("tidak bisa clear screen");
        }
    }
	
}
