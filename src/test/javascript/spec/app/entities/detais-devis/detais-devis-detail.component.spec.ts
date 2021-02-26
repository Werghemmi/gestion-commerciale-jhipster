import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { WerghemmiGestionCommerclaieTestModule } from '../../../test.module';
import { DetaisDevisDetailComponent } from 'app/entities/detais-devis/detais-devis-detail.component';
import { DetaisDevis } from 'app/shared/model/detais-devis.model';

describe('Component Tests', () => {
  describe('DetaisDevis Management Detail Component', () => {
    let comp: DetaisDevisDetailComponent;
    let fixture: ComponentFixture<DetaisDevisDetailComponent>;
    const route = ({ data: of({ detaisDevis: new DetaisDevis(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [WerghemmiGestionCommerclaieTestModule],
        declarations: [DetaisDevisDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(DetaisDevisDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DetaisDevisDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load detaisDevis on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.detaisDevis).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
