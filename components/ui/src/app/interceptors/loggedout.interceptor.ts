import { Injectable } from '@angular/core';
import {HttpRequest, HttpHandler, HttpEvent, HttpInterceptor, HttpResponse} from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import {catchError, map} from 'rxjs/operators';

@Injectable()
export class LoggedoutInterceptor implements HttpInterceptor {
    constructor() { }

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        return next.handle(request).pipe(
            map((event: HttpEvent<any>) => {
                if (event instanceof HttpResponse && event.body && event.body.zone_name && event.body.zone_name === 'uaa') {
                    location.reload(true)
                }

                return event
            }),
            catchError(err => {
                if ([401, 403].indexOf(err.status) !== -1) {
                    location.reload(true);
                }

                const error = err.error.message || err.statusText;
                return throwError(error);
            })
        )
    }
}