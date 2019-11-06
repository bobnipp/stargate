import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core'
import {animate, state, style, transition, trigger} from "@angular/animations";
import {RfiLink} from "../../models/rfi-link.model";
import {timer} from "rxjs";

@Component({
    selector: 'stargate-sidebar-existing-link',
    templateUrl: './sidebar-existing-link.component.html',
    styleUrls: ['../sidebar.component.scss','../form.scss', './sidebar-existing-link.component.scss'],
    animations: [
        trigger('animateState', [
            state('highlighted', style({
                backgroundColor: '#e7f0e2'
            })),
            state('default', style({
                backgroundColor: 'white',
            })),
            transition('highlighted => default', animate('1s 0s ease-out')),
            transition('default => highlighted', animate('1s 0s ease-out'))
        ])
    ]
})
export class SidebarExistingLinkComponent implements OnInit {
    @Input()
    rfiId: number | string

    @Input()
    link: RfiLink

    @Input()
    index: number

    @Output()
    delete: EventEmitter<number> = new EventEmitter<number>()

    @Output()
    openLink: EventEmitter<RfiLink> = new EventEmitter<RfiLink>()

    highlightState: string = 'default'
    linkClass: string = 'link-id'
    displayedMenuIndex: number = -1

    constructor() {
    }

    ngOnInit(): void {
        this.generateLinkClass()
    }

    deleteLink() {
        this.delete.emit(this.index)
    }

    openLinkedObject(link: RfiLink) {
        if (this.link.title || this.link.status) {
            this.openLink.emit(link)
        }
    }

    highlight() {
        this.highlightState = 'highlighted'
        timer(1500).subscribe(() => {
            this.highlightState = 'default'
        })
    }

    toggleMenu(index: number) {
        if (this.displayedMenuIndex < 0) {
            this.displayedMenuIndex = index
        } else {
            this.displayedMenuIndex = -1
        }
    }

    closeMenu() {
        this.displayedMenuIndex = -1
    }

    private generateLinkClass() {
        this.linkClass = (!this.link.title && !this.link.status) ?
            'link-id-broken' : 'link-id'
    }
}
