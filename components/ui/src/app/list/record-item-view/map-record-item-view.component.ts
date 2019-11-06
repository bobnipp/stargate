import {Component, EventEmitter, Input, OnChanges, Output, SimpleChanges} from '@angular/core'
import {BaseRecordItemViewComponent} from './base-record-item-view.component'
import {RecordItem} from '../../models/record.model'
import {ChangeTabAction, CloseSidebarAction, ReceivedOpenLinksAction} from '../../state/actions'

@Component({
    selector: 'map-record-item-view',
    templateUrl: './base-record-item-view.component.html',
    styleUrls: ['./base-record-item-view.component.scss', './map-record-item-view.component.scss']
})
export class MapRecordItemViewComponent extends BaseRecordItemViewComponent implements OnChanges {

    @Input()
    activeRecordIds: string[] = []

    @Output()
    revealLocation: EventEmitter<string> = new EventEmitter<string>()

    isActive: false

    ngOnChanges(changes: SimpleChanges): void {
        if (changes['activeRecordIds']) {
            this.isActive = changes['activeRecordIds'].currentValue.includes(this.record.id.toString())
        }
    }

    doOpenLinkList(record: RecordItem) {
        if (!!record) {
            if (!this.openLinks.find(rec => rec.id === record.id)) {
                this.store.dispatch(new CloseSidebarAction())
                this.store.dispatch(new ReceivedOpenLinksAction([...this.openLinks, record]))
            }
        }
        this.onTabSelect('LIST')
    }

    onTabSelect(event: any) {
        this.store.dispatch(new ChangeTabAction(event))
    }

    fireRevealLocation(id) {
        this.revealLocation.emit(id)
    }

}