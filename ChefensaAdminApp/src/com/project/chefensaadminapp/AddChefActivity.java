package com.project.chefensaadminapp;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.project.chefensaadminapp.util.ChefensaAdminAppUtil;

public class AddChefActivity extends ActionBarActivity {
	
	private EditText nameView, phoneNoView, emailView, descView, languagesView, ethnicityView, mealTypesView, specialityView, capacityVIew,
	addressView, localityView, cityView, stateView, pinVIew, landmarkView;
	private Context mContext;
	private RadioGroup radioGenderGroup, maritalStatusGroup, chefCategoryGroup, mealCategoryGroup, workingTimeGroup;
	private CheckBox mon, tue, wed, thu, fri, sat, sun;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_chef);
		
		mContext = this;
		
		addressView = (EditText)findViewById(R.id.initial_address_input);
		localityView = (EditText)findViewById(R.id.locality_input);
		cityView = (EditText)findViewById(R.id.city_input);
		stateView = (EditText)findViewById(R.id.state_input);
		pinVIew = (EditText)findViewById(R.id.pin_input);
		landmarkView = (EditText)findViewById(R.id.landmark_input);
		
		nameView = (EditText)findViewById(R.id.name_input);
		phoneNoView = (EditText)findViewById(R.id.phone_no_input);
		emailView = (EditText)findViewById(R.id.email_input);
		descView = (EditText)findViewById(R.id.desc_input);
		languagesView = (EditText)findViewById(R.id.language_input);
		ethnicityView = (EditText)findViewById(R.id.ethnicity_input);
		mealTypesView = (EditText)findViewById(R.id.mealtype_input);
		specialityView = (EditText)findViewById(R.id.speciality_input);
		capacityVIew = (EditText)findViewById(R.id.capacity_input);
		radioGenderGroup = (RadioGroup)findViewById(R.id.radioGender);
		maritalStatusGroup = (RadioGroup)findViewById(R.id.radioMaritalStatus);
		chefCategoryGroup = (RadioGroup)findViewById(R.id.radioChefType);
		mealCategoryGroup = (RadioGroup)findViewById(R.id.radioMealType);
		workingTimeGroup = (RadioGroup)findViewById(R.id.radioWorkingTime);
		mon = (CheckBox)findViewById(R.id.mon);
		tue = (CheckBox)findViewById(R.id.tue);
		wed = (CheckBox)findViewById(R.id.wed);
		thu = (CheckBox)findViewById(R.id.thu);
		fri = (CheckBox)findViewById(R.id.fri);
		sat = (CheckBox)findViewById(R.id.sat);
		sun = (CheckBox)findViewById(R.id.sun);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.add_chef, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.add_chef) {
			String name = nameView.getText().toString();
			int gender = ChefensaAdminAppUtil.GENDER_MALE;;
			switch(radioGenderGroup.getCheckedRadioButtonId()){
			case R.id.radioMale:
				gender = ChefensaAdminAppUtil.GENDER_MALE;
				break;
			case R.id.radioFemale:
				gender = ChefensaAdminAppUtil.GENDER_FEMALE;
				break;
			}
			String phoneNo = phoneNoView.getText().toString();
			String email = emailView.getText().toString();
			String intialAddress = addressView.getText().toString();
			String locality = localityView.getText().toString();
			String city = cityView.getText().toString();
			String state = stateView.getText().toString();
			String pin = pinVIew.getText().toString();
			String landmark = landmarkView.getText().toString();
			
			String desc = descView.getText().toString();
			String languages = languagesView.getText().toString();
			int maritalStatus = ChefensaAdminAppUtil.MARITAL_STATUS_UNMARRIED;
			switch (maritalStatusGroup.getCheckedRadioButtonId()) {
			case R.id.radioUnmarried:
				maritalStatus = ChefensaAdminAppUtil.MARITAL_STATUS_UNMARRIED;
				break;
			case R.id.radioMarried:
				maritalStatus = ChefensaAdminAppUtil.MARITAL_STATUS_MARRIED;
				break;
			}
			String ethnicity = ethnicityView.getText().toString();
			int chefCategory = ChefensaAdminAppUtil.CHEF_TYPE_HOUSE_WIFE;
			switch(chefCategoryGroup.getCheckedRadioButtonId()){
			case R.id.radioHouseWife:
				chefCategory = ChefensaAdminAppUtil.CHEF_TYPE_HOUSE_WIFE;
				break;
			case R.id.radioVendor:
				chefCategory = ChefensaAdminAppUtil.CHEF_TYPE_VENDOR;
				break;
			}
			int mealCategory = ChefensaAdminAppUtil.MEAL_TYPE_VEG;
			switch (mealCategoryGroup.getCheckedRadioButtonId()) {
			case R.id.radioVeg:
				mealCategory = ChefensaAdminAppUtil.MEAL_TYPE_VEG;
				break;
			case R.id.radioEgg:
				mealCategory = ChefensaAdminAppUtil.MEAL_TYPE_EGG;
				break;
			case R.id.radioNonVeg:
				mealCategory = ChefensaAdminAppUtil.MEAL_TYPE_NON_VEG;
				break;
			}
			String workingDays = "";
			if(mon.isChecked()){
				workingDays = workingDays + "1";
			} else {
				workingDays = workingDays + "0";
			}
			if(tue.isChecked()){
				workingDays = workingDays + "1";
			} else {
				workingDays = workingDays + "0";
			}
			if(wed.isChecked()){
				workingDays = workingDays + "1";
			} else {
				workingDays = workingDays + "0";
			}
			if(thu.isChecked()){
				workingDays = workingDays + "1";
			} else {
				workingDays = workingDays + "0";
			}
			if(fri.isChecked()){
				workingDays = workingDays + "1";
			} else {
				workingDays = workingDays + "0";
			}
			if(sat.isChecked()){
				workingDays = workingDays + "1";
			} else {
				workingDays = workingDays + "0";
			}
			if(sun.isChecked()){
				workingDays = workingDays + "1";
			} else {
				workingDays = workingDays + "0";
			}
			int workingTime = ChefensaAdminAppUtil.WORKING_TIME_MORNING;
			switch(workingTimeGroup.getCheckedRadioButtonId()){
			case R.id.radioMorning:
				workingTime = ChefensaAdminAppUtil.WORKING_TIME_MORNING;
				break;
			case R.id.radioEvening:
				workingTime = ChefensaAdminAppUtil.WORKING_TIME_EVENING;
				break;
			case R.id.radioBoth:
				workingTime = ChefensaAdminAppUtil.WORKING_TIME_BOTH;
				break;
			}
			String mealTypes = mealTypesView.getText().toString();
			String speciality = specialityView.getText().toString();
			String capacity = capacityVIew.getText().toString();
			if((name == null || name.equals("")) || (phoneNo == null || phoneNo.equals("")) 
					|| (mealTypes == null || mealTypes.equals("")) ||(capacity == null || capacity.equals(""))){
				Toast.makeText(this, "* marked fields are manadatory", Toast.LENGTH_SHORT).show();
			} else {
				final JSONObject json = new JSONObject();
				try {
					json.put("chefName", name);
					json.put("gender", gender);
					json.put("phoneNumber", phoneNo);
					json.put("emailId", email);
					json.put("initialAddress", intialAddress);
					json.put("locality", locality);
					json.put("city", city);
					json.put("state", state);
					json.put("pin", pin);
					json.put("landmark", landmark);
					json.put("chefDescription", desc);
					json.put("languages", languages);
					json.put("maritalStatus", maritalStatus);
					json.put("ethnicity", ethnicity);
					json.put("chefCategory", chefCategory);
					json.put("mealCategory", mealCategory);
					json.put("workingDays", workingDays);
					json.put("workingTime", workingTime);
					json.put("mealTypes", mealTypes);
					json.put("chefSpeciality", speciality);
					json.put("capacity", capacity);
					new AsyncTask<JSONObject, Void, String>() {
						ProgressDialog pd= new ProgressDialog(mContext);
						@Override
						protected void onPreExecute() {
							pd.setMessage("Please wait while Chef Data is being saved");
							pd.show();
						}
						
						@Override
						protected String doInBackground(JSONObject... params) {
							pd.setMessage("Saving data ....");
							return ChefensaAdminAppUtil
									.sendHttpRequest(ChefensaAdminAppUtil.SERVER_URL + "Chefensa-WebService/chef/create",
											json.toString());
						}
						
						@Override
						protected void onPostExecute(String result) {
							pd.setMessage("Chef data has been successfullly saved");
							pd.dismiss();
							onBackPressed();
						}
					}.execute(json);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
