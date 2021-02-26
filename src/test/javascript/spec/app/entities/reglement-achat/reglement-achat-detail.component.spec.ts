import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { WerghemmiGestionCommerclaieTestModule } from '../../../test.module';
import { ReglementAchatDetailComponent } from 'app/entities/reglement-achat/reglement-achat-detail.component';
import { ReglementAchat } from 'app/shared/model/reglement-achat.model';

describe('Component Tests', () => {
  describe('ReglementAchat Management Detail Component', () => {
    let comp: ReglementAchatDetailComponent;
    let fixture: ComponentFixture<ReglementAchatDetailComponent>;
    const route = ({ data: of({ reglementAchat: new ReglementAchat(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [WerghemmiGestionCommerclaieTestModule],
        declarations: [ReglementAchatDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ReglementAchatDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ReglementAchatDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load reglementAchat on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.reglementAchat).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
