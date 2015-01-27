package com.project.chefensa;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.project.chefensa.adapter.NavDrawerListAdapter;
import com.project.chefensa.db.ChefensaDataSource;
import com.project.chefensa.fragment.ContactUsFragment;
import com.project.chefensa.fragment.HowItWorksFragment;
import com.project.chefensa.fragment.MyOrdersFragment;
import com.project.chefensa.fragment.MyProfileFragment;
import com.project.chefensa.fragment.TabbedFragment;
import com.project.chefensa.model.Address;
import com.project.chefensa.model.Customer;
import com.project.chefensa.model.Meal;
import com.project.chefensa.model.NavDrawerItem;
import com.project.chefensa.model.Order;
import com.project.chefensa.util.ConstantUtil;
import com.project.chefensa.util.StringUtils;

@SuppressWarnings("deprecation")
public class ChefensaFragmentActivity extends FragmentActivity {

	List<Meal> todaysLunchList = new ArrayList<Meal>();
	List<Meal> todaysDinnerList = new ArrayList<Meal>();

	private Context mContext;

	private String[] navMenuTitles;
	private TypedArray navMenuIcons;
	private ArrayList<NavDrawerItem> navDrawerItems;
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
	private NavDrawerListAdapter adapter;
	private Order currentOrder;
	private Dialog cartDialog;
	
	private Customer customer;
	private List<Address> addressList;
	private List<Meal> cartList;
	private int addressSelectedType = 0; // 0 :none selected, 1:
											// SpinnerSelected, 2: new Address
											// selected
	LinearLayout orderConfirmLayout;
	ScrollView orderDetailScroll;
	boolean isFromOrder = false;
	int cartTotal = 0;
	
	private static final int HOME_FRAGMENT = 0;
	private static final int MY_ORDERS_FRAGMENT = 1;
	private static final int MY_PROFILE_FRAGMENT = 2;
	private static final int HOW_IT_WORKS_FRAGMENT = 3;
	private static final int  CONTACT_US_FRAGMENT = 4;
	
	private int fragmentType;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		mContext = this;

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.drawer_list_item);
		cartDialog = new Dialog(mContext);
		
		cartDialog.setCancelable(true);
		getSupportFragmentManager().beginTransaction()
				.add(R.id.container, new TabbedFragment()).commit();
		fragmentType = HOME_FRAGMENT;
		

		mDrawerList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				switch (position) {
				case 0:
					getSupportFragmentManager().beginTransaction()
							.replace(R.id.container, new TabbedFragment())
							.addToBackStack(null).commit();
					fragmentType = HOME_FRAGMENT;
					break;
				case 1:
					getSupportFragmentManager().beginTransaction()
							.replace(R.id.container, new MyOrdersFragment())
							.addToBackStack(null).commit();
					fragmentType = MY_ORDERS_FRAGMENT;
					break;
				case 2:
					getSupportFragmentManager().beginTransaction()
							.replace(R.id.container, new MyProfileFragment())
							.addToBackStack(null).commit();
					fragmentType = MY_PROFILE_FRAGMENT;
					break;
				case 3:
					getSupportFragmentManager().beginTransaction()
							.replace(R.id.container, new HowItWorksFragment())
							.addToBackStack(null).commit();
					fragmentType = HOW_IT_WORKS_FRAGMENT;
					break;
				case 4:
					getSupportFragmentManager().beginTransaction()
							.replace(R.id.container, new ContactUsFragment())
							.addToBackStack(null).commit();
					fragmentType = CONTACT_US_FRAGMENT;
					break;
				}
				invalidateOptionsMenu();
				mDrawerLayout.closeDrawer(mDrawerList);
			}
		});

		navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);
		navMenuIcons = getResources()
				.obtainTypedArray(R.array.nav_drawer_icons);
		navDrawerItems = new ArrayList<NavDrawerItem>();
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons
				.getResourceId(0, -1)));
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons
				.getResourceId(1, -1)));
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons
				.getResourceId(2, -1)));
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons
				.getResourceId(3, -1)));
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons
				.getResourceId(4, -1)));
		navMenuIcons.recycle();

		adapter = new NavDrawerListAdapter(this, navDrawerItems);
		mDrawerList.setAdapter(adapter);

		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		customer = ChefensaDataSource.getInstance(mContext).getCustomer();
		addressList = ChefensaDataSource.getInstance(mContext).getAllAddress();
		cartList = new ArrayList<Meal>();

		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, R.string.app_name, R.string.app_name) {
			public void onDrawerClosed(View view) {
				invalidateOptionsMenu();
			}

			public void onDrawerOpened(View drawerView) {
				invalidateOptionsMenu();
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		cartDialog.setCanceledOnTouchOutside(true);
		cartDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
			@Override
			public boolean onKey(DialogInterface dialog, int keyCode,
					KeyEvent event) {
				if (keyCode == KeyEvent.KEYCODE_BACK && !event.isCanceled()) {
					if (orderConfirmLayout != null && orderDetailScroll != null) {
						if (orderConfirmLayout.getVisibility() == View.VISIBLE) {
							orderDetailScroll.setVisibility(View.VISIBLE);
							orderConfirmLayout.setVisibility(View.GONE);
							isFromOrder = true;
						} else if (orderDetailScroll.getVisibility() == View.VISIBLE
								&& !isFromOrder) {
							cartDialog.dismiss();
						} else if (isFromOrder) {
							isFromOrder = false;
						}
					} else {
						cartDialog.dismiss();
					}

					return true;
				}
				return false;
			}
		});
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		ConstantUtil.lunchList.clear();
		ConstantUtil.dinnerList.clear();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		if (item.getItemId() == R.id.action_cart) {
			showCart();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	public void showCart() {

		cartDialog.setContentView(R.layout.cart_dialog_new);
		cartDialog.setTitle(R.string.cart_string);

		WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
		lp.copyFrom(cartDialog.getWindow().getAttributes());
		lp.width = WindowManager.LayoutParams.MATCH_PARENT;
		cartDialog.getWindow().setAttributes(lp);

		// ListView mealListView =
		// (ListView)cartDialog.findViewById(R.id.cart_meal_list);
		final LinearLayout customerDetail = (LinearLayout) cartDialog
				.findViewById(R.id.customer_details);
		final LinearLayout addressListLayout = (LinearLayout) cartDialog
				.findViewById(R.id.address_list_layout);
		final LinearLayout enterAddressLayout = (LinearLayout) cartDialog
				.findViewById(R.id.details_edit_layout);
		LinearLayout mealLayout = (LinearLayout) cartDialog
				.findViewById(R.id.cart_meal_layout);
		orderConfirmLayout = (LinearLayout) cartDialog
				.findViewById(R.id.order_confirm_layout);
		final LinearLayout emptyCartLayout = (LinearLayout) cartDialog
				.findViewById(R.id.empty_cart_layout);
		orderDetailScroll = (ScrollView) cartDialog
				.findViewById(R.id.order_detail_scroll);
		final Spinner addressSpinner = (Spinner) cartDialog
				.findViewById(R.id.address_spinner);
		final Button addressAddButton = (Button) cartDialog
				.findViewById(R.id.add_address_button);
		final EditText nameText = (EditText) cartDialog
				.findViewById(R.id.name_text);
		final EditText phoneText = (EditText) cartDialog
				.findViewById(R.id.phone_text);
		final EditText emailText = (EditText) cartDialog
				.findViewById(R.id.email_text);
		final EditText flatNoText = (EditText) cartDialog
				.findViewById(R.id.flat_no_text);
		final EditText buildingText = (EditText) cartDialog
				.findViewById(R.id.building_name_text);
		final EditText streetText = (EditText) cartDialog
				.findViewById(R.id.street_text);
		final EditText localityText = (EditText) cartDialog
				.findViewById(R.id.locality_text);
		final EditText pinCodeText = (EditText) cartDialog
				.findViewById(R.id.pin_code_text);
		final EditText landmarkText = (EditText) cartDialog
				.findViewById(R.id.landmark_text);
		final TextView confirmNameText = (TextView) cartDialog
				.findViewById(R.id.order_confirm_name);
		final TextView confirmPhoneText = (TextView) cartDialog
				.findViewById(R.id.order_confirm_phone);
		final TextView confirmEmailText = (TextView) cartDialog
				.findViewById(R.id.order_confirm_email);
		final TextView confirmAddressText = (TextView) cartDialog
				.findViewById(R.id.order_confirm_address);
		final TextView confirmTotalPriceText = (TextView) cartDialog
				.findViewById(R.id.order_confirm_totalprice);
		final TextView totalPriceText = (TextView) cartDialog
				.findViewById(R.id.cart_total_price_view);
		final Button confirmButton = (Button) cartDialog
				.findViewById(R.id.confirm_button);
		final Button placeOrderButton = (Button) cartDialog
				.findViewById(R.id.place_order_button);
		final Button payNowButton = (Button) cartDialog
				.findViewById(R.id.pay_now_button);
		final Button codButton = (Button) cartDialog
				.findViewById(R.id.cod_button);

		getMealsInCart();
		totalPriceText.setText("" + cartTotal);
		if (cartList.isEmpty()) {
			emptyCartLayout.setVisibility(View.VISIBLE);
			orderConfirmLayout.setVisibility(View.GONE);
			orderDetailScroll.setVisibility(View.GONE);
		} else {
			emptyCartLayout.setVisibility(View.GONE);
			orderConfirmLayout.setVisibility(View.GONE);
			orderDetailScroll.setVisibility(View.VISIBLE);
		}
		getCartMealListView(mealLayout, totalPriceText);
		// mealListView.setAdapter(new CartMealListAdapter(mContext,cartList));
		if (customer != null) {
			if (!StringUtils.isNullOrEmpty(customer.getCustomerName())) {
				nameText.setText(customer.getCustomerName());
			}
			if (!StringUtils.isNullOrEmpty(customer.getPrimaryPhone())) {
				phoneText.setText(customer.getPrimaryPhone());
			}
			if (!StringUtils.isNullOrEmpty(customer.getPrimaryEmail())) {
				emailText.setText(customer.getPrimaryEmail());
			}
		}
		if (addressList != null && !addressList.isEmpty()) {
			enterAddressLayout.setVisibility(View.GONE);
			addressAddButton.setVisibility(View.VISIBLE);
			addressListLayout.setVisibility(View.VISIBLE);
		} else {
			addressSelectedType = 2;
			enterAddressLayout.setVisibility(View.VISIBLE);
			addressAddButton.setVisibility(View.GONE);
			addressListLayout.setVisibility(View.GONE);
		}
		addressAddButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				addressSelectedType = 2;
				enterAddressLayout.setVisibility(View.VISIBLE);
			}
		});
		/*
		 * addressSpinner.setOnItemClickListener(new OnItemClickListener() {
		 * 
		 * @Override public void onItemClick(AdapterView<?> parent, View view,
		 * int position, long id) { addressSelectedType =1; //todo: add code for
		 * getting addressId } });
		 */
		placeOrderButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// getSupportFragmentManager().beginTransaction().replace(R.id.container,
				// new MealOrderFragment()).addToBackStack(null).commit();
				customerDetail.setVisibility(View.VISIBLE);
				placeOrderButton.setVisibility(View.INVISIBLE);
				// cartDialog.dismiss();
			}
		});
		confirmButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (!StringUtils.isNullOrEmpty(phoneText.getText().toString())
						&& !StringUtils.isNullOrEmpty(nameText.getText()
								.toString())) {
					Customer cust = new Customer();
					cust.setCustomerName(nameText.getText().toString());
					cust.setPrimaryPhone(phoneText.getText().toString());
					cust.setPrimaryEmail(emailText.getText().toString());
					ChefensaDataSource.getInstance(mContext).addCustomer(cust);
					currentOrder = new Order();
					currentOrder.setCustomerName(nameText.getText().toString());
					currentOrder.setPhoneNumber(phoneText.getText().toString());
					currentOrder.setCustomerEmail(emailText.getText()
							.toString());
					currentOrder.setTotalPrice(cartTotal);
					if (addressSelectedType == 2) {
						Address address = new Address();
						String add = flatNoText.getText().toString()
								+ buildingText.getText().toString()
								+ streetText.getText().toString()
								+ localityText.getText().toString()
								+ pinCodeText.getText().toString()
								+ landmarkText.getText().toString();
						if (!StringUtils.isNullOrEmpty(add)) {
							address.setAddressText(add);
							currentOrder.setAddress(add);
							// ChefensaDataSource.getInstance(mContext).addAddress(address);
							orderConfirmLayout.setVisibility(View.VISIBLE);
							orderDetailScroll.setVisibility(View.GONE);
							cartDialog.setCanceledOnTouchOutside(false);
							isFromOrder = false;
							confirmNameText.setText("Name : "
									+ currentOrder.getCustomerName());
							confirmPhoneText.setText("Phone : "
									+ currentOrder.getPhoneNumber());
							confirmEmailText.setText("Email : "
									+ currentOrder.getCustomerEmail());
							confirmAddressText.setText("Address : "
									+ currentOrder.getAddress());
							confirmTotalPriceText.setText("Total Rs."
									+ currentOrder.getTotalPrice());
						} else {
							Toast.makeText(mContext,
									"Please provide an address",
									Toast.LENGTH_SHORT).show();
						}
					} else if (addressSelectedType == 1) {
						// todo: add code for spinner similar as above extra for
						// address
						orderConfirmLayout.setVisibility(View.VISIBLE);
						orderDetailScroll.setVisibility(View.GONE);
						cartDialog.setCanceledOnTouchOutside(false);
						isFromOrder = false;
					} else {
						Toast.makeText(mContext, "Please provide an address",
								Toast.LENGTH_SHORT).show();
					}

				} else {
					Toast.makeText(mContext,
							"Either Name or Phone field is Empty",
							Toast.LENGTH_SHORT).show();
				}

			}
		});
		payNowButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

			}
		});
		codButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

			}
		});

		cartDialog.show();
	}

	private void getMealsInCart() {
		List<Meal> mealList = ChefensaDataSource.getInstance(mContext)
				.getMeals();
		cartList.clear();
		cartTotal = 0;
		for (int i = 0; i < mealList.size(); i++) {
			Meal element = mealList.get(i);
			if (element.getMealCount() > 0) {
				cartList.add(element);
				cartTotal = cartTotal + element.getMealPrice()
						* element.getMealCount();
			}
		}
	}

	private void getCartMealListView(final LinearLayout mealLayout,
			final TextView totalTextView) {
		int i = 0;
		for (Iterator<Meal> iterator = cartList.iterator(); iterator.hasNext();) {
			Meal meal = iterator.next();
			View view = ((LayoutInflater) mContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
					.inflate(R.layout.cart_list_item_view, null);
			TextView countView = (TextView) view
					.findViewById(R.id.item_number_view);
			TextView mealNameView = (TextView) view
					.findViewById(R.id.meal_name_view);
			TextView mealConView = (TextView) view
					.findViewById(R.id.meal_content_view);
			TextView mealQuantityView = (TextView) view
					.findViewById(R.id.meal_quantity_view);
			TextView mealPriceView = (TextView) view
					.findViewById(R.id.meal_price_view);
			i++;
			countView.setText(i + ".");
			mealNameView.setText(meal.getMealName());
			mealConView.setText(meal.getMealContent());
			mealQuantityView.setText(meal.getMealQuantity() + "");
			mealPriceView.setText(meal.getMealPrice() + "");
			ImageView removeButton = (ImageView) view
					.findViewById(R.id.remove_from_cart_button);
			removeButton.setTag("removecartitem" + (i - 1));
			removeButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					if (v.getTag() != null) {
						if (v.getTag() instanceof String) {
							String tag = (String) v.getTag();
							int position = Integer.parseInt(tag.substring(14,
									15));
							Meal meal = cartList.get(position);
							cartTotal = cartTotal - meal.getMealPrice()
									* meal.getMealCount();
							totalTextView.setText("" + cartTotal);
							cartList.remove(position);
							mealLayout.removeViewAt(position);
						}
					}
				}
			});
			mealLayout.addView(view);
		}
		return;
	}
}
