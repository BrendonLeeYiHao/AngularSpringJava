import { inject } from "@angular/core";
import { Router, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, CanActivateFn } from "@angular/router";
import { AuthService } from "./auth.service";
import { Observable } from "rxjs";


export const AuthGuard: CanActivateFn = (
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
    ):
    Observable<boolean | UrlTree> 
    | Promise<boolean | UrlTree> 
    | boolean 
    | UrlTree=> {

    return inject(AuthService).isLoggedIn()
        ? true
        : inject(Router).createUrlTree(['/login']);
};