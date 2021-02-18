<%-- 
    Document   : addAPet
    Created on : 8-Feb-2021, 3:38:37 PM
    Author     : Cashton
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>InfinitePets</title>
    <link rel="stylesheet" href="./assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="./assets/fonts/fontawesome-all.min.css">
    <link rel="stylesheet" href="./assets/fonts/font-awesome.min.css">
    <link rel="stylesheet" href="./assets/fonts/fontawesome5-overrides.min.css">
    <link rel="stylesheet" href="./assets/css/styles.min.css">
</head>

<body style="background: #e7e4d9;color: rgb(255,255,255);">
    <header style="background: #9a9891;">
        <div class="row align-items-center" style="height: 75px;margin: 0px;">
            <div class="col d-flex justify-content-start" style="margin: 0px;padding-left: 20px;padding-right: 0px;">
                <div class="d-flex justify-content-center align-items-center" style="padding: 10px;border-radius: 12px;padding-right: 15px;padding-left: 15px;border-width: 1px;border-style: none;background: rgba(0,0,0,0);"><i class="fas fa-bars" style="font-size: 26px;"></i></div>
            </div>
            <div class="col-auto d-flex justify-content-center" style="margin: 0px;margin-right: 0px;margin-left: 0px;"><img id="logoLarge" src="./assets/img/Logos/White/infinite%20pets%20logo%20-%20black%20(4).png" style="width: 250px;"></div>
            <div class="col d-flex justify-content-end" style="margin: 0px;padding-right: 20px;padding-left: 0px;">
                <div class="d-flex justify-content-center align-items-center" style="padding: 13px;border-radius: 43px;padding-right: 15px;padding-left: 15px;border-width: 1px;border-style: none;background: rgba(0,0,0,0);width: 45px;height: 45px;"><i class="fas fa-user" style="color: rgb(255,255,255);font-size: 26px;"></i></div>
            </div>
        </div>
    </header>
    <div class="container" style="max-width: 1014px;height: 36px;margin-top: 10px;margin-bottom: 10px;"><a href="#" style="color: #9a9891;">My Pets</a><i class="fas fa-chevron-right" style="margin-right: 5px;margin-left: 5px;color: rgba(154,152,145,0.49);"></i><a href="#" style="color: #9a9891;">Add a Pet</a></div>
    <div class="container shadow-none d-flex justify-content-center align-items-center" style="max-width: none;margin-top: 10px;margin-bottom: 10px;">
        <div class="container d-flex justify-content-center align-items-center" style="width: 850px;background: rgba(255,255,255,0.5);border-radius: 25px;height: 425px;">
            <form>
                <div class="form-row">
                    <div class="col">
                        <h1 style="margin-right: 15px;margin-left: 15px;color: #777673;font-size: 30px;border-color: rgba(119,118,115,0.27);border-bottom-width: 2px;border-bottom-style: solid;">Add a Pet</h1>
                    </div>
                </div>
                <div class="form-row">
                    <div class="col" style="margin-left: 20px;margin-right: 10px;">
                        <div class="form-row" style="margin-top: 10px;margin-bottom: 10px;">
                            <div class="col-8"><label style="margin-bottom: 1px;color: rgba(154,152,145,0.65);">Pet name</label><input class="form-control" type="text" style="height: 36px;"></div>
                        </div>
                        <div class="form-row" style="margin-bottom: 10px;">
                            <div class="col"><label style="margin-bottom: 1px;color: rgba(154,152,145,0.65);">Animal</label>
                                <div class="dropdown"><button class="btn btn-dark dropdown-toggle" aria-expanded="false" data-toggle="dropdown" type="button" style="background: rgba(154,152,145,0.75);border-style: none;">Dropdown </button>
                                    <div class="dropdown-menu"><a class="dropdown-item" href="#">First Item</a></div>
                                </div>
                            </div>
                            <div class="col"><label style="margin-bottom: 1px;color: rgba(154,152,145,0.65);">Breed</label>
                                <div class="dropdown border-light shadow-none"><button class="btn btn-dark dropdown-toggle" aria-expanded="false" data-toggle="dropdown" type="button" style="background: rgba(154,152,145,0.75);border-style: none;">Dropdown </button>
                                    <div class="dropdown-menu"><a class="dropdown-item" href="#">First Item</a></div>
                                </div>
                            </div>
                        </div>
                        <div class="form-row" style="margin-bottom: 10px;">
                            <div class="col">
                                <div class="form-row">
                                    <div class="col" style="margin-bottom: 10px;"><label style="margin-bottom: 1px;color: rgba(154,152,145,0.65);">Medical information</label><textarea class="form-control" style="width: 310px;height: 115px;max-height: 200px;"></textarea></div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col" style="margin-left: 10px;margin-right: 20px;">
                        <div class="form-row" style="margin-top: 10px;margin-bottom: 10px;">
                            <div class="col" style="margin-bottom: 10px;">
                                <div class="form-row">
                                    <div class="col" style="margin-bottom: 10px;"><label style="margin-bottom: 1px;color: rgba(154,152,145,0.65);">Sex</label>
                                        <div class="dropdown border-light shadow-none" style="width: 134px;"><button class="btn btn-dark dropdown-toggle" aria-expanded="false" data-toggle="dropdown" type="button" style="background: rgba(154,152,145,0.75);border-style: none;">Dropdown </button>
                                            <div class="dropdown-menu"><a class="dropdown-item" href="#">First Item</a></div>
                                        </div>
                                    </div>
                                    <div class="col"><label style="margin-bottom: 1px;color: rgba(154,152,145,0.65);">Birthday</label><input class="form-control" type="date" style="width: 171px;height: 36px;"></div>
                                </div>
                            </div>
                            <div class="col d-flex flex-column align-items-start align-content-center" style="margin-bottom: 10px;margin-left: 10px;padding: 0px;"><label style="margin-bottom: 1px;color: rgba(154,152,145,0.65);">Upload a photo</label><i class="fa fa-photo" style="font-size: 50px;border-radius: 5px;border: 2px solid rgba(154,152,145,0.75);background: rgb(255,255,255);text-align: center;width: 120px;color: rgba(0,0,0,0.21);margin-bottom: 5px;padding: 18px;"></i><button class="btn btn-dark" type="button" style="background: rgba(154,152,145,0.75);border-style: none;width: 118px;">Browse</button></div>
                            <div class="col"><label style="margin-bottom: 1px;color: rgba(154,152,145,0.65);">Preferred Veterinarian</label>
                                <div class="form-row" style="margin-bottom: 10px;">
                                    <div class="col"><input class="form-control" type="text" style="height: 36px;"></div>
                                </div>
                            </div>
                        </div>
                        <div class="form-row d-flex justify-content-start" style="margin-bottom: 10px;">
                            <div class="col-auto"><button class="btn btn-danger" type="button" style="background: rgba(154,152,145,0.75);border-style: none;">Cancel</button></div>
                            <div class="col-auto"><button class="btn btn-success" type="button" style="background: rgba(154,152,145,0.75);border-style: none;">Save</button></div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <div class="container" style="background: #9a9891;max-width: none;position: initial;bottom: 0px;margin-top: 30px;">
        <div class="row" style="margin: 2px;padding: 10px;">
            <div class="col-4">
                <div class="row" style="margin: 0px;margin-top: 5px;margin-bottom: 5px;">
                    <div class="col" style="padding: 0px;">
                        <h4 class="d-flex justify-content-xl-start" style="font-size: 18px;margin-bottom: 0px;">Contact Us</h4>
                    </div>
                </div>
                <div class="row" style="margin-top: 5px;margin-bottom: 5px;margin-right: 0px;margin-left: 0px;">
                    <div class="col-6 d-flex justify-content-xl-start" style="padding: 0px;font-size: 12px;"><a href="4039889244" style="color: rgba(0,0,0,0.33);">403-988-9244</a></div>
                </div>
                <div class="row" style="margin: 0px;margin-top: 5px;margin-bottom: 5px;">
                    <div class="col-6 d-flex justify-content-xl-start" style="padding: 0px;font-size: 12px;"><a href="infinitepetcare@gmail.com" style="color: rgba(0,0,0,0.33);">infinitepetcare@gmail.com</a></div>
                </div>
                <div class="row" style="margin: 0px;margin-top: 5px;margin-bottom: 5px;margin-right: 0px;">
                    <div class="col-2 d-flex justify-content-start align-items-center" style="font-size: 12px;padding: 0px;"><i class="fab fa-instagram" style="color: rgba(0,0,0,0.33);font-size: 24px;"></i></div>
                    <div class="col-2 d-flex justify-content-start align-items-center" style="font-size: 12px;padding: 0px;"><i class="fab fa-facebook-square" style="color: rgba(0,0,0,0.33);font-size: 24px;text-align: center;"></i></div>
                </div>
            </div>
            <div class="col-4">
                <div class="row" style="margin: 0px;margin-top: 5px;margin-bottom: 5px;">
                    <div class="col" style="padding: 0px;">
                        <h4 class="d-flex justify-content-xl-start" style="font-size: 18px;margin-bottom: 0px;">Sign up for our newsletter!</h4>
                    </div>
                </div>
                <div class="row" style="margin-top: 5px;margin-bottom: 5px;margin-right: 0px;margin-left: 0px;">
                    <div class="col" style="padding: 0px;">
                        <p style="font-size: 12px;max-width: 220px;color: rgba(0,0,0,0.33);margin: 0px;">Receive notifications about upcoming events, new services, recommended products and more!</p>
                    </div>
                </div>
                <div class="row" style="margin: 0px;margin-top: 5px;margin-bottom: 5px;">
                    <div class="col-6" style="padding: 0px;">
                        <form><input class="shadow-none form-control form-control-sm" type="email" placeholder="Email" style="background: rgba(255,255,255,0.2);color: rgb(255,255,255);border-style: none;border-color: rgba(0,0,0,0.4);margin: 5px;margin-left: 0px;margin-right: 0px;margin-top: 0px;"><button class="btn btn-light btn-sm shadow-none" type="button" style="background: rgba(0,0,0,0.2);color: #ffffff;border: 1px none rgba(0,0,0,0.2);">Subscribe</button></form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="./assets/js/jquery.min.js"></script>
    <script src="./assets/bootstrap/js/bootstrap.min.js"></script>
</body>

</html>