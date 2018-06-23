package com.exchange.activity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.exchange.entity.CATEGORY;
import com.exchange.entity.FormFile;
import com.exchange.entity.KIND;
import com.exchange.tool.HttpRequestTool;
import com.exchange.tool.SocketHttpRequester;
import com.exchange.tool.TransformUnit;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ReleaseActivity extends Activity {
	private ImageView btnReleaseBack;
	private LinearLayout btnSelectPicture;
	private ViewPager viewPager; // 对应的viewPager
	private List<View> viewList = new ArrayList<View>();;// view数组
	private TextView btnPinkage;
	private TextView btnNewDegree;
	private boolean selectStatePinkage = false;
	private boolean selectStateNewDegree = false;
	private Dialog dialogPrice;
	private View inflate;
	private EditText cName;
	private EditText cDetail;
	private EditText etPrice;
	private EditText etOldPrice;
	private boolean etPriceIsFocus;
	private boolean etOldPriceIsFocus;
	private TextView tvPrice;
	private String OldPrice;
	private TextView tvCategory;
	private int kindPid = -1; // 要查询子关联种类的父分类标识码
	private TextView tvKind;
	private TextView tvExchangeCategory;
	private int yourChoice;
	private String[] categoryItems;
	private int[] categoryItemsCode;
	private String[] kindItems;
	private String[] kindItemsCode;
	private File file;
	private int commodity_Ptype;
	private String commodity_type;
	private int commodity_Etype;
	private String releaseImagesUrl = "";
	// 声明定义此Activity的Handler
	public Handler handlerShowCategorySelect = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0:
				Bundle data = msg.getData();
				String jsonString = data.getString("value").trim();
				Gson gson = new Gson();
				List<CATEGORY> categorys = gson.fromJson(jsonString,
						new TypeToken<List<CATEGORY>>() {
						}.getType());
				int categoryNum = categorys.size();
				int i = 0;
				categoryItems = new String[categoryNum];
				categoryItemsCode = new int[categoryNum];
				Iterator iterator = categorys.iterator();
				while (iterator.hasNext()) {
					CATEGORY category = (CATEGORY) iterator.next();
					categoryItems[i] = category.getCategory_name();
					categoryItemsCode[i] = category.getCategory_id();
					i++;
				}
				yourChoice = -1;
				AlertDialog.Builder singleChoiceDialog = new AlertDialog.Builder(
						ReleaseActivity.this);
				singleChoiceDialog.setTitle("请选择您出售的商品类型");
				// 第二个参数是默认选项，此处设置为0
				singleChoiceDialog.setSingleChoiceItems(categoryItems, 0,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								yourChoice = which;
								commodity_Ptype = categoryItemsCode[yourChoice];
							}
						});
				singleChoiceDialog.setPositiveButton("确定",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								if (yourChoice != -1) {
									tvCategory
											.setText(categoryItems[yourChoice]);
									tvKind.setText("请重新选择出售的商品种类");
									kindPid = categoryItemsCode[yourChoice];
								}
							}
						});
				singleChoiceDialog.show();
				break;
			default:
				break;
			}
		}
	};
	public Handler handlerShowKindSelect = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0:
				Bundle data = msg.getData();
				String jsonString = data.getString("value").trim();
				Gson gson = new Gson();
				List<KIND> kinds = gson.fromJson(jsonString,
						new TypeToken<List<KIND>>() {
						}.getType());
				int kindNum = kinds.size();
				int i = 0;
				kindItems = new String[kindNum];
				kindItemsCode = new String[kindNum];
				Iterator iterator = kinds.iterator();
				while (iterator.hasNext()) {
					KIND kind = (KIND) iterator.next();
					kindItems[i] = kind.getKind_name();
					kindItemsCode[i] = kind.getKind_Id();
					i++;
				}
				yourChoice = -1;
				AlertDialog.Builder singleChoiceDialog = new AlertDialog.Builder(
						ReleaseActivity.this);
				singleChoiceDialog.setTitle("请选择您出售的商品种类");
				// 第二个参数是默认选项，此处设置为0
				singleChoiceDialog.setSingleChoiceItems(kindItems, 0,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								yourChoice = which;
								commodity_type = kindItemsCode[yourChoice];
							}
						});
				singleChoiceDialog.setPositiveButton("确定",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								if (yourChoice != -1) {
									tvKind.setText(kindItems[yourChoice]);
								}
							}
						});
				singleChoiceDialog.show();
				break;
			default:
				break;
			}
		}
	};
	public Handler handlerShowExchangeCategorySelect = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0:
				Bundle data = msg.getData();
				String jsonString = data.getString("value").trim();
				Gson gson = new Gson();
				List<CATEGORY> categorys = gson.fromJson(jsonString,
						new TypeToken<List<CATEGORY>>() {
						}.getType());
				int categoryNum = categorys.size();
				int i = 0;
				categoryItems = new String[categoryNum];
				categoryItemsCode = new int[categoryNum];
				Iterator iterator = categorys.iterator();
				while (iterator.hasNext()) {
					CATEGORY category = (CATEGORY) iterator.next();
					categoryItems[i] = category.getCategory_name();
					categoryItemsCode[i] = category.getCategory_id();
					i++;
				}
				yourChoice = -1;
				AlertDialog.Builder singleChoiceDialog = new AlertDialog.Builder(
						ReleaseActivity.this);
				singleChoiceDialog.setTitle("请选择您出售的商品类型");
				// 第二个参数是默认选项，此处设置为0
				singleChoiceDialog.setSingleChoiceItems(categoryItems, 0,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								yourChoice = which;
								commodity_Etype = categoryItemsCode[yourChoice];
							}
						});
				singleChoiceDialog.setPositiveButton("确定",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								if (yourChoice != -1) {
									tvExchangeCategory
											.setText(categoryItems[yourChoice]);
								}
							}
						});
				singleChoiceDialog.show();
				break;
			default:
				break;
			}
		}
	};
	public Handler handlerReleaseCommodity = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0:
				Bundle data = msg.getData();
				String jsonString = data.getString("value").trim();
				if (jsonString.equals("成功")) {
					Toast.makeText(ReleaseActivity.this, "发布商品成功",
							Toast.LENGTH_SHORT).show();
					finish();
				}
				break;
			default:
				break;
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_release);
		btnReleaseBack = (ImageView) findViewById(R.id.btnReleaseBack);
		btnSelectPicture = (LinearLayout) findViewById(R.id.btnSelectPicture);
		viewPager = (ViewPager) findViewById(R.id.viewpager);
		btnPinkage = (TextView) findViewById(R.id.btnPinkage);
		btnNewDegree = (TextView) findViewById(R.id.btnNewDegree);
		tvPrice = (TextView) findViewById(R.id.tvPrice);
		tvCategory = (TextView) findViewById(R.id.tvCategory);
		tvKind = (TextView) findViewById(R.id.tvKind);
		tvExchangeCategory = (TextView) findViewById(R.id.tvExchangeCategory);
		cName = (EditText) findViewById(R.id.etCName);
		cDetail = (EditText) findViewById(R.id.etCDetail);
		btnReleaseBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		btnSelectPicture.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 调用android自带的图库
				Intent intent = new Intent(
						Intent.ACTION_PICK,
						android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				startActivityForResult(intent, 1);
			}
		});
		btnPinkage.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				selectStatePinkage = !selectStatePinkage;
				btnPinkage.setSelected(selectStatePinkage);
				btnPinkage.setTextColor(selectStatePinkage ? Color.WHITE
						: Color.BLACK);
			}
		});
		btnNewDegree.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				selectStateNewDegree = !selectStateNewDegree;
				btnNewDegree.setSelected(selectStateNewDegree);
				btnNewDegree.setTextColor(selectStateNewDegree ? Color.WHITE
						: Color.BLACK);
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (data != null) {
			if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
				String fileName = getRealPathFromUri(ReleaseActivity.this,
						data.getData());
				file = new File(fileName);
			}
			Thread thread = new Thread(runnable);
			thread.start();
			addPic(data);
		}
	}

	Runnable runnable = new Runnable() {

		public void run() {
			uploadFile(file);
		}

	};

	/**
	 * 上传图片到服务器
	 * 
	 * @param imageFile
	 *            包含路径
	 */
	public void uploadFile(File imageFile) {
		System.out.println("upload start");
		String username = "获取不到用户名";
		try {
			SharedPreferences sharedPreferences = getSharedPreferences(
					"userInfo", Context.MODE_PRIVATE);
			String imgaeFileName = sharedPreferences.getString("user_id",
					"null") + new Date().getTime() + ".jpg";
			System.out.println(imgaeFileName);
			releaseImagesUrl = releaseImagesUrl + "upload/" + imgaeFileName
					+ ";";
			String requestUrl = "http://192.168.191.1:8086/exchange/upload/execute.action";
			// 请求普通信息
			Map<String, String> params = new HashMap<String, String>();
			params.put("username",
					sharedPreferences.getString("user_name", "null"));
			params.put("pwd",
					sharedPreferences.getString("user_password", "null"));
			params.put("age", "21");
			params.put("fileName", imgaeFileName);
			// 上传文件
			FormFile formfile = new FormFile(imageFile.getName(), imageFile,
					"image", "application/octet-stream");

			SocketHttpRequester.post(requestUrl, params, formfile);
			System.out.println("upload success");
		} catch (Exception e) {
			System.out.println("upload error");
			e.printStackTrace();
		}
		System.out.println("upload end");
	}

	public static String getRealPathFromUri(Context context, Uri contentUri) {
		Cursor cursor = null;
		try {
			String[] proj = { MediaStore.Images.Media.DATA };
			cursor = context.getContentResolver().query(contentUri, proj, null,
					null, null);
			int column_index = cursor
					.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
			cursor.moveToFirst();
			return cursor.getString(column_index);
		} finally {
			if (cursor != null) {
				cursor.close();
			}
		}
	}

	// 调用android自带图库，显示选中的图片
	private void addPic(Intent data) {
		Uri selectedImage = data.getData();
		System.out.println(selectedImage);
		// 缩放图片, width, height 按相同比例缩放图片
		BitmapFactory.Options options = new BitmapFactory.Options();
		// options 设为true时，构造出的bitmap没有图片，只有一些长宽等配置信息，但比较快，设为false时，才有图片
		options.inJustDecodeBounds = true;
		Bitmap bitmap;
		int scale = (int) (options.outWidth / (float) 300);
		if (scale <= 0)
			scale = 1;
		options.inSampleSize = scale;
		options.inJustDecodeBounds = false;
		try {
			bitmap = BitmapFactory.decodeStream(getContentResolver()
					.openInputStream(selectedImage), null, options);
		} catch (FileNotFoundException e) {
			Toast.makeText(this, "找不到文件", Toast.LENGTH_SHORT).show();
			e.printStackTrace();
			return;
		}
		ImageView imageview = new ImageView(this);
		imageview.setImageBitmap(bitmap);
		imageview.setMaxHeight(350);
		viewList.add(imageview);
		PagerAdapter pagerAdapter = new PagerAdapter() {
			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				return arg0 == arg1;
			}

			@Override
			public int getCount() {
				return viewList.size();
			}

			@Override
			public void destroyItem(ViewGroup container, int position,
					Object object) {
				container.removeView(viewList.get(position));
			}

			@Override
			public Object instantiateItem(ViewGroup container, int position) {
				container.addView(viewList.get(position));
				return viewList.get(position);
			}
		};
		LinearLayout.LayoutParams vpll = (LinearLayout.LayoutParams) viewPager
				.getLayoutParams();
		int vpheight = TransformUnit.dip2px(this, 200);
		vpll.height = vpheight;
		viewPager.setLayoutParams(vpll);
		viewPager.setAdapter(pagerAdapter);
	}

	public void showPriceSelect(View view) {
		dialogPrice = new Dialog(this);
		// 设置关闭监听
		dialogPrice.setOnDismissListener(new OnDismissListener() {
			@Override
			public void onDismiss(DialogInterface dialog) {
				etPriceIsFocus = false;
				etOldPriceIsFocus = false;
			}
		});
		// 填充对话框的布局
		inflate = LayoutInflater.from(this)
				.inflate(R.layout.dialog_price, null);
		// 初始化控件
		etPrice = (EditText) inflate.findViewById(R.id.etPrice);
		etOldPrice = (EditText) inflate.findViewById(R.id.etOldPrice);
		// 设置不弹出软键盘
		etPrice.setInputType(InputType.TYPE_NULL);
		etOldPrice.setInputType(InputType.TYPE_NULL);
		etPrice.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus) {
					// 获得焦点
					etPriceIsFocus = true;
				} else {
					// 失去焦点
					etPriceIsFocus = false;
				}
			}
		});
		etOldPrice.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus) {
					// 获得焦点
					etOldPriceIsFocus = true;
				} else {
					// 失去焦点
					etOldPriceIsFocus = false;
				}
			}
		});
		// 将布局设置给Dialog
		dialogPrice.setContentView(inflate);
		// 获取当前Activity所在的窗体
		Window dialogWindow = dialogPrice.getWindow();
		// 设置Dialog从窗体底部弹出
		dialogWindow.setGravity(Gravity.BOTTOM);
		// 获得窗体的属性
		WindowManager.LayoutParams lp = dialogWindow.getAttributes();
		lp.y = 20;// 设置Dialog距离底部的距离
		// 将属性设置给窗体
		dialogWindow.setAttributes(lp);
		dialogPrice.show();// 显示对话框
	}

	public void cilckMyKeyboard(View view) {
		String priceTemp = "";
		switch (view.getId()) {
		case R.id.one:
			priceTemp = "1";
			break;
		case R.id.two:
			priceTemp = "2";
			break;
		case R.id.three:
			priceTemp = "3";
			break;
		case R.id.four:
			priceTemp = "4";
			break;
		case R.id.five:
			priceTemp = "5";
			break;
		case R.id.six:
			priceTemp = "6";
			break;
		case R.id.seven:
			priceTemp = "7";
			break;
		case R.id.eight:
			priceTemp = "8";
			break;
		case R.id.nine:
			priceTemp = "9";
			break;
		case R.id.zero:
			priceTemp = "0";
			break;
		case R.id.dot:
			priceTemp = ".";
			break;
		case R.id.c:
			if (etPriceIsFocus) {
				etPrice.setText("");
			}
			if (etOldPriceIsFocus) {
				etOldPrice.setText("");
			}
			break;
		case R.id.cancel:
			dialogPrice.dismiss();
			break;
		case R.id.enter:
			String price = etPrice.getText().toString().trim() + priceTemp;
			String oldPrice = etOldPrice.getText().toString().trim()
					+ priceTemp;
			OldPrice = oldPrice;
			tvPrice.setText(price);
			dialogPrice.hide();
			break;
		default:
			break;
		}
		if (etPriceIsFocus) {
			String text = etPrice.getText().toString().trim() + priceTemp;
			etPrice.setText(text);
		}
		if (etOldPriceIsFocus) {
			String text = etOldPrice.getText().toString().trim() + priceTemp;
			etOldPrice.setText(text);
		}
	}

	public void showCategorySelect(View view) {
		HttpRequestTool.sendHttpRequest(
				getResources().getString(R.string.ServerIP)
						+ "categoryQuery.action",
				new HashMap<String, String>(), handlerShowCategorySelect);
	}

	public void showKindSelect(View view) {
		if (kindPid == -1) {
			Toast.makeText(ReleaseActivity.this, "请先选择出售商品分类",
					Toast.LENGTH_SHORT).show();
			return;
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("kind_Pid", kindPid + "");
		HttpRequestTool.sendHttpRequest(
				getResources().getString(R.string.ServerIP)
						+ "kindQueryByPid.action", map, handlerShowKindSelect);
	}

	public void showExchangeCategorySelect(View view) {
		HttpRequestTool.sendHttpRequest(
				getResources().getString(R.string.ServerIP)
						+ "categoryQuery.action",
				new HashMap<String, String>(),
				handlerShowExchangeCategorySelect);
	}

	public void releaseCommodity(View view) throws UnsupportedEncodingException {
		SharedPreferences sharedPreferences = getSharedPreferences("userInfo",
				Context.MODE_PRIVATE);
		String address = sharedPreferences.getString("user_address", "没拿到");
		String seller_id = sharedPreferences.getString("user_id", "没拿到");
		System.out.println(cName.getText());
		System.out.println(cDetail.getText());
		System.out.println(tvPrice.getText());
		System.out.println(OldPrice);
		System.out.println(address);
		System.out.println(commodity_Ptype);
		System.out.println(commodity_type);
		System.out.println(commodity_Etype);
		System.out.println(releaseImagesUrl);
		Map<String, String> map = new HashMap<String, String>();
		map.put("commodity.commodity_name", URLEncoder.encode(cName.getText().toString(), "utf-8"));
		map.put("commodity.commodity_detail", URLEncoder.encode(cDetail.getText().toString(), "utf-8"));
		map.put("commodity.commodity_imgUrl", releaseImagesUrl);
		map.put("commodity.commodity_type", commodity_type);
		map.put("commodity.commodity_Ptype", commodity_Ptype + "");
		map.put("commodity.commodity_originalPrice", OldPrice);
		map.put("commodity.commodity_expectPrice", tvPrice.getText().toString());
		map.put("commodity.commodity_degree", URLEncoder.encode("九成新", "utf-8"));
		map.put("commodity.commodity_exchangeSth", commodity_Etype + "");
		map.put("commodity.commodity_sellerAddress", URLEncoder.encode(address, "utf-8"));
//		map.put("commodity.commodity_sellerAddress", "&#x53D1;&#x987A;&#x4E30;&#x52A0;&#x5FEB;&#x901F;&#x5EA6;&#x8985;&#x673A;");
		
		map.put("commodity.seller_id", seller_id);
		HttpRequestTool.sendHttpRequest(
				getResources().getString(R.string.ServerIP)
						+ "commodityStores.action", map,
				handlerReleaseCommodity);
	}
}
