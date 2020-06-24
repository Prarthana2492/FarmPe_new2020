package com.SevenNine.Essentials;

public class Urls {

    private static final String ROOT_URL = "http://52.66.200.98:8383/api/";///DEV
    //private static final String ROOT_URL = "http://13.233.184.72:9090/api/";///pro
    // private static final String ROOT_URL = "http://3.17.6.57:9393/api/";///DEV
    // private static final String ROOT_URL = "http://3.17.6.57:8888//api/";//PRO


    public static final String IMAGE_ROOT_URL = "http://13.232.185.209:909";//Image root
    // public static final String Add_New_Address = ROOT_URL+"MasterTable/AddUserAddress";
    public static final String Add_New_Address = ROOT_URL+"MasterTable/AddUserAddressDetails";
    public static final String GetFarmerDetailsList = ROOT_URL+"MasterTable/GetFarmersList";

    public static final String LOGIN=ROOT_URL+"Auth/ValidateUser";
    public static final String SIGNUP=ROOT_URL+"Auth/RegisterUser";
    public static final String LOANSIGNUP="http://13.232.185.209:9292/api/Auth/RegisterNewUser";
    public static final String NEWSIGNUP=ROOT_URL+"Auth/RegisterNewUser";
    public static final String NEWSIGNUPDealer="http://13.232.185.209:9696/api/Auth/RegisterNewUser";
    public static final String NEWLOGIN=ROOT_URL+"Auth/ValidateRegisteredUser";
    public static final String GetAllCrops=ROOT_URL+"Crops/GetCrops";
    public static final String AddToCart=ROOT_URL+"Order/AddToCart";
    public static final String Languages=ROOT_URL+"MasterTable/GetLanguages";
    public static final String Forgot_Password=ROOT_URL+"Auth/ForgotPassword";
    public static final String ChangePassword=ROOT_URL+"Auth/ChangePassword";
    public static final String ResendOTP=ROOT_URL +"Auth/ResendOTPforUser";
    public static final String VerifyOTPNewUser=ROOT_URL+"Auth/VerifyOTPNewUser";
    public static final String GetUserDetails=ROOT_URL+"GetUserDetails";
    public static final String ValidateReferalCode=ROOT_URL+"ValidateRefferalCode";
   public static final String CHANGE_LANGUAGE= ROOT_URL+"Lang/ChangeCurrentCulture";
    public static final String AddBankAccounts=ROOT_URL+"MasterTable/AddUpdateBankDetails";
    public static final String GetBankList = ROOT_URL+"MasterTable/GetBankDetailsList";


    // public static final String CHANGE_LANGUAGE= "http://13.232.185.209:909/api/Lang/ChangeCurrentCulture";



    public static final String Get_New_Address = ROOT_URL+"MasterTable/GetUserAddress";
    // Wallet
    public static final String GetFarmDetailsList = ROOT_URL+"MasterTable/GetFarmsList";
    public static final String GetFarmsListByUserId = ROOT_URL+"MasterTable/GetFarmsListByUserId";


    // Refer n Earn
    public static final String Refferal_Code = ROOT_URL +"Auth/GetUserDetails";


    //Wallet balance
    public static final String GetWalletDetails = ROOT_URL +"MasterTable/GetWalletDetails";


    // Address
    public static final String Delete_Address_Details = ROOT_URL + "MasterTable/DeleteUserAddress";
    public static final String Default_Address = ROOT_URL + "MasterTable/UpdateUserDefaultAddress";
    public static final String Edit_Address = ROOT_URL + "MasterTable/UpdateUserDefaultAddress";


    //feedback
    public static final String AddFeedback = ROOT_URL + "MasterTable/AddFeedback";


    //profile details
    public static final String Get_Profile_Details= ROOT_URL + "Auth/GetRegUserDetails";
    public static final String Update_Profile_Details= ROOT_URL + "Auth/UpdateRegProfile";


    //Notification

    public static final String GET_NOTIFICATION= ROOT_URL + "MasterTable/GetNotificationMaster";
    public static final String GET_NOTIFICATIONLIST= ROOT_URL + "MasterTable/GetNotifications";
    public static final String UPDATEUSERNOTIFICATIONSETTING= ROOT_URL + "Auth/UpdateUserNotificationSettings";

    // http://3.17.6.57:8686/api/Auth/UpdateUserNotificationSettings

    public static final String Districts=ROOT_URL+"MasterTable/GetDistricts";
    public static final String Taluks=ROOT_URL+"MasterTable/GetBlocks";
    public static final String Hoblis=ROOT_URL+"MasterTable/GetBlocks";
    public static final String Villages=ROOT_URL+"MasterTable/GetVillagebyBlock";
    public static final String State = ROOT_URL+"MasterTable/GetStatesList";
    public static final String Blocks = ROOT_URL+"MasterTable/GetBlocks";
    public static final String GetVillagebyGram = ROOT_URL+"MasterTable/GetVillagebyGram";
    public static final String GetVillagebyBlock = ROOT_URL+"MasterTable/GetVillagebyBlock";


    public static final String GetBrandList = ROOT_URL+"MasterTable/GetBrandList";
    public static final String ModelList = ROOT_URL+"MasterTable/GetModels";
    public static final String GetHPList = ROOT_URL+"MasterTable/GetHPList";
    public static final String AddRequestForQuotation = ROOT_URL+"MasterTable/AddUpdateRequestForQuotation";
    public static final String GetLookingForItems = ROOT_URL+"MasterTable/GetLookingForDetails";
    public static final String GetLookingForFirst = ROOT_URL+"MasterTable/GetLookingFor";
    public static final String GetLookingForList = ROOT_URL+"MasterTable/GetLookingForLists";
    public static final String YourRequest = ROOT_URL+"MasterTable/GetLookingForListsById";



    //List Your Farms
    public static final String List_Your_Farms = ROOT_URL+"MasterTable/GetFarmCategoryList";
    public static final String Farm_Type_List = ROOT_URL+"MasterTable/GetFarmTypesList";
    public static final String Farm_Details = ROOT_URL+"MasterTable/AddUpdateFarms";
    public static final String AddUpdateFarms = ROOT_URL+"MasterTable/AddUpdateFarms";
    public static final String Invitation_Farm = ROOT_URL+"MasterTable/GetInvitationList";
    public static final String Invitn_accpt_cancel = ROOT_URL+"MasterTable/RespondToConnectionRequest";
    public static final String AddUpdateSelling = ROOT_URL+"SellingType/AddUpdateSellingDetails";


    //verify mobile number
    public static final String Get_Verify_Mobile_Number = ROOT_URL+"SellingType/GetVMobileNumber";


    //Connections
    public static final String Get_Connection_List = ROOT_URL + "MasterTable/GetConnectionList";


    //Homepage_Count
    public static final String Home_Page_Count = ROOT_URL + "MasterTable/GetCountForFarmer";


    //Notification
    public static final String Notification_HomePage = ROOT_URL + "MasterTable/GetNotifications";


    // Farm Category
    public static final String FarmCategory_List = ROOT_URL + "MasterTable/GetFarmCategoryList";


    // Add Farms camera
    public static final String AddFarms = ROOT_URL + "MasterTable/AddUpdateFarms";
    public static final String AddOwner = ROOT_URL + "MasterTable/AddFarmsOwernership";
    public static final String GetFarmNameDetails = ROOT_URL + "MasterTable/GetFarmNameDetails";


    //  get Soil type
    public static final String GetSoilType = ROOT_URL + "MasterTable/GetSoilDetails";
    public static final String Add_Land_Records = ROOT_URL + "MasterTable/AddUpdateLandRecords";
    public static final String Add_Irrigation = ROOT_URL + "MasterTable/AddUpdateIrrigationDetails";


    //new API 04/12/2019
    public static final String AddUpdateNewFarmDetails = ROOT_URL + "SellingType/AddUpdateNewFarmDetails";
    public static final String AddfarmOwnershipDetails = ROOT_URL + "SellingType/AddUpdatefarmOwnershipDetails";
    public static final String AddLandRecordsDetails = ROOT_URL + "SellingType/AddUpdateLandRecordsDetails";
    public static final String AddFarmContactDetails = ROOT_URL + "SellingType/AddUpdateFarmContactDetails";
    public static final String AddFCultivationDetails = ROOT_URL + "SellingType/AddUpdateFCultivationDetails";
    public static final String AddUserAddressDetails = ROOT_URL + "MasterTable/AddUserAddressDetails";
    public static final String GetUserAddress = ROOT_URL + "MasterTable/GetUserAddress";
    public static final String AddFarmPondDetails = ROOT_URL + "SellingType/AddUpdateFarmPondDetails";
    public static final String AddBankDetails = ROOT_URL + "MasterTable/AddUpdateBankDetails";
    public static final String GetBankDetails = ROOT_URL + "MasterTable/GetBankDetailsList";
    public static final String AddNewFarms = ROOT_URL + "MasterTable/AddUpdateFarms";
    public static final String GetAddNewFarms = ROOT_URL + "MasterTable/GetFarmNameDetails";
    public static final String GetSellingType = ROOT_URL + "MasterTable/GetSellingTypeList";
    public static final String GetProductDetailsList = ROOT_URL + "SevenNine/GetProductDetailsList";
    public static final String GetFavCartList = ROOT_URL + "SevenNine/GetFavCartList";
    public static final String AddUpdateCartProductDetails = ROOT_URL + "SevenNine/AddUpdateCartProductDetails";
    public static final String AddUpateCartCheckoutDetails = ROOT_URL + "SevenNine/AddUpateCartCheckoutDetails";
    public static final String GetOrderList = ROOT_URL + "SevenNine/GetOrderList";
    public static final String GetCartProductDetails = ROOT_URL + "SevenNine/GetCartProductDetails";
    public static final String DeleteReviewCartList = ROOT_URL + "SevenNine/DeleteReviewCartList";
    public static final String AddUpdateFavouriteCartList = ROOT_URL + "SevenNine/AddUpdateFavouriteCartList";
 public static final String GetReviewCount = ROOT_URL + "SevenNine/GetReviewCount";
 public static final String GetSellingCategoryList = ROOT_URL + "MasterTable/GetSellingCategoryList";
    public static final String GetSellingList = ROOT_URL + "MasterTable/GetSellingList";
    public static final String Get_Profile_Details1= ROOT_URL + "Auth/GetRegUserDetails";

    public static final String GetCropTypeList = ROOT_URL + "MasterTable/GetCropTypeList";
    public static final String GetCropCategoryList = ROOT_URL + "MasterTable/GetCropCategoryList";
    public static final String GetCropLists = ROOT_URL + "MasterTable/GetCropLists";
    public static final String AddCropDetails = ROOT_URL + "SellingType/AddUpdateCropDetails";
    public static final String GetCropVarietyList = ROOT_URL + "MasterTable/GetCropVarietyList";
    public static final String GetCropQualityList = ROOT_URL + "MasterTable/GetCropQualityList";
    public static final String GetCropQuantityList = ROOT_URL + "MasterTable/GetCropQuantityList";


    public static final String GetCropUnitOfQuantityList= ROOT_URL + "MasterTable/GetCropUnitOfQuantityList ";
    public static final String GetEventsList= ROOT_URL +"MasterTable/GetFEventLists";
    public static final String GetSoilDetailsLists=ROOT_URL +"MasterTable/GetSoilDetailsList";
    public static final String GetWRUFarmingLists=ROOT_URL +"MasterTable/GetWRUFarmingList";
    public static final String GetFIrriragationTypesList=ROOT_URL +"MasterTable/GetFIrriragationTypesList";
    public static final String GetFIrrigationMethodsLists=ROOT_URL +"MasterTable/GetFIrrigationMethodsLists";
    public static final String GetFIrrigationCoverageList=ROOT_URL +"MasterTable/GetFIrrigationCoverageList";
    public static final String GetSellingVarietyList=ROOT_URL +"MasterTable/GetSellingVarietyList";
    public static final String GetSellingQualityList=ROOT_URL +"MasterTable/GetSellingQualityList";
    public static final String GetSellingQuantityList=ROOT_URL +"MasterTable/GetSellingQuantityList";
    public static final String GetUnitOfPriceList=ROOT_URL +"MasterTable/GetUnitOfPriceList";

    // public static final String GetCropUnitOfQuantityList= ROOT_URL + "MasterTable/GetCropUnitOfQuantityList";


    public static final String GetFEventLists= ROOT_URL + "MasterTable/GetFEventLists";
    public static final String DeleteUserAddress= ROOT_URL + "MasterTable/DeleteUserAddress";
    public static final String DeleteBankDetails= ROOT_URL + "MasterTable/DeleteBankDetails";
    public static final String GetLandRecordDetails= ROOT_URL + "SellingType/GetLandRecordDetails";
    public static final String GetFOwnershipDetails = ROOT_URL + "SellingType/GetFOwnershipDetails";
    public static final String GetPondDetails = ROOT_URL + "SellingType/GetPondDetails";
    public static final String AddPondDetails = ROOT_URL + "SellingType/AddUpdateFarmPondDetails";


  //  http://13.232.185.209:9898/api/MasterTable/GetFarmNameDetails


    public static final String Order_processed=ROOT_URL+"OrderDetails/OrderProcessed";
    public static final String PayuMoneyAdd= ROOT_URL+"SevenNine/AddPayUTransactionDetails";
    public static final String GetGIContactDetails= ROOT_URL+"SellingType/GetFContactDetails";
    public static final String AddSoilDetails= ROOT_URL+"SellingType/AddUpdateSoilDetails";
    public static final String GetSoilDetails= ROOT_URL+"SellingType/GetSoilDetails";
    public static final String AddIrrigationDetails= ROOT_URL+"SellingType/AddUpdateIrrigationDetails";
    public static final String GetIrrigationDetails= ROOT_URL+"SellingType/GetIrrigationDetails";
    public static final String AddUpdateEventDetails= ROOT_URL+"SellingType/AddUpdateEventDetails";
    public static final String GetEventDetails= ROOT_URL+"SellingType/GetEventDetails";
    public static final String SendOTPForMobileNumber= ROOT_URL+"Auth/SendOTPForMobileNumber";
    public static final String AddUpdateVMobilenumber= ROOT_URL+"SellingType/AddUpdateVMobilenumber";
    public static final String GetSellDetails= ROOT_URL+"SellingType/GetSellDetails";
    public static final String GetSellDetailsbySellId= ROOT_URL+"SellingType/GetSellDetailsbySellId";
    public static final String AddUpdateFirmDetails= ROOT_URL+"MasterTable/AddUpdateFirmDetails";
    public static final String AddUpdateShopCImageDetails= ROOT_URL+"MasterTable/AddUpdateCImageDetails";
    public static final String GetCImagelist= ROOT_URL+"MasterTable/GetCImagelist";
    public static final String GetCLocationList= ROOT_URL+"MasterTable/GetCLocationList";
    public static final String AddUpdateCLocationDetails= ROOT_URL+"MasterTable/AddUpdateCLocationDetails";
    public static final String GetUserVerificationStatus= ROOT_URL+"MasterTable/GetUserVerificationStatus";
    public static final String GetOrdersFromCart= ROOT_URL+"SellingType/GetOrdersFromCart";
    public static final String AddUpdateUserDetails= ROOT_URL+"MasterTable/AddUpdateUserDetails";
    public static final String EditSellDetails= ROOT_URL+"SellingType/UpdateSellDetails";


}

//http://13.232.185.209:9898/api/MasterTable/AddUserAddressDetails