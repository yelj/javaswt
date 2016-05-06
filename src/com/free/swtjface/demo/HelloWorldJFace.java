package com.free.swtjface.demo;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.free.swtjface.demo.Utils.MyUtils;
import com.free.swtjface.demo.Utils.StringUtils;
import com.free.swtjface.demo.bean.Account;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public class HelloWorldJFace extends ApplicationWindow {
	public HelloWorldJFace() {
		super(null);
	}
	protected Control createContents(Composite parent) {
		getShell().setText("hello world! Window");
		parent.setSize(400, 250);

		return parent;
	}
	public static void main(String[] args) {
		Display display = new Display ();
		Shell shell = new Shell (display);
		Label label = new Label (shell, SWT.NONE);
		label.setText ("用户名:");
		Text text = new Text (shell, SWT.BORDER);
		text.setLayoutData (new RowData (100, SWT.DEFAULT));

		Label labelMima = new Label (shell, SWT.NONE);
		labelMima.setText ("密码：");
		Text textPwd = new Text (shell, SWT.BORDER);
		textPwd.setLayoutData (new RowData (100, SWT.DEFAULT));

		Button ok = new Button (shell, SWT.PUSH);
		ok.setText ("OK");
		ok.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("OK");			




				gotoLogin();
			}
		});
		Button cancel = new Button (shell, SWT.PUSH);
		cancel.setText ("Cancel");
		cancel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				System.out.println("Cancel");
			}
		});
		shell.setDefaultButton (cancel);
		shell.setLayout (new RowLayout ());
		shell.pack ();
		shell.open ();
		while (!shell.isDisposed ()) {
			if (!display.readAndDispatch ()) display.sleep ();
		}
		display.dispose ();

	}
	protected static void gotoLogin() {
		String pwd = StringUtils.getMD5Str("456789" + "gsdef");
		Account   account = new Account();
		account.setUsername("18689662551");
		account.setPassword(pwd);
		account.setPlatform("android");
		account.setSystemversion("11");
		account.setVersion("6");
		account.setClientid("");
		// TODO Auto-generated method stub
		JSONObject    jsObj = new JSONObject ();
		try {
			//jsObj.
			jsObj.put("username", account.getUsername());
			jsObj.put("password", account.getPassword());
			jsObj.put("clientid", account.getClientid());
			jsObj.put("platform", account.getPlatform());
			jsObj.put("version", account.getVersion());
			//jsObj.put("size", 11);
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		System.out.println("out:"+jsObj.toString());
		Map<String, String> paramMap=new HashMap<String, String>();
		paramMap.put("username", account.getUsername());
		paramMap.put("password", account.getPassword());
		paramMap.put("clientid", account.getClientid());
		paramMap.put("platform", account.getPlatform());
		paramMap.put("version", account.getVersion());
		
		MyUtils.post("http://sev.ichongxin.com/server/user/login"
				,account,jsObj,
				paramMap);
	}


} 