import { AuthService } from "../services/auth-service/auth.service";

export function AuthInitializerProviderFactory(authService: AuthService){
    return()=>{
        return new Promise((resolve,reject)=>{
            authService.init().then(()=>{
                resolve(true);
            }).catch(()=>{
                //reject(false);
            })
    
        });
    }

}