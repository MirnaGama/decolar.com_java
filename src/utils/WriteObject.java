package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class WriteObject {

	public void saveObject(Object obj, String url) {

		FileOutputStream fout = null;
		ObjectOutputStream oos = null;

		try {

			fout = new FileOutputStream(url);
			oos = new ObjectOutputStream(fout);
			oos.writeObject(obj);

			// System.out.println("Done - File write in: " + url);

		} catch (Exception ex) {

			ex.printStackTrace();

		} finally {

			if (fout != null) {
				try {
					fout.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (oos != null) {
				try {
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}

	public boolean existFile(String url) {

		boolean result = false;
		FileInputStream fint = null;
		ObjectInput ois = null;
		try {

			fint = new FileInputStream(url);
			ois = new ObjectInputStream(fint);

			// System.out.println("Done - File read in: " + url);
			result = true;
		} catch (FileNotFoundException fn) {

		} catch (Exception ex) {

			ex.printStackTrace();

		} finally {

			if (fint != null) {
				try {
					fint.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	public Object loadObject(String url) {

		FileInputStream fint = null;
		ObjectInput ois = null;
		Object result = null;
		try {

			fint = new FileInputStream(url);
			ois = new ObjectInputStream(fint);
			result = ois.readObject();

			// System.out.println("Done - File read in: " + url);

		} catch (Exception ex) {

			ex.printStackTrace();

		} finally {

			if (fint != null) {
				try {
					fint.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

}
